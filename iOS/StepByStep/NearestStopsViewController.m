//
//  NearestStopsViewController.m
//  StepByStep
//
//  Gets the nearest stops and shows them in a UITableView
//

#import "NearestStopsViewController.h"
#import "StopsMapViewController.h"
#import <MapKit/MapKit.h>
#import "AFHTTPRequestOperationManager.h"
#import "Busstop.h"
#import "StopCell.h"
#import "TimesForStopViewController.h"

@interface NearestStopsViewController ()
@property (strong, nonatomic) IBOutlet UITableView *stopsTableView;

@property (nonatomic, strong) __block NSArray *stopsList;
@property AFHTTPRequestOperationManager *manager;

@end

@implementation NearestStopsViewController
@synthesize stopsList;
@synthesize stopsTableView;
@synthesize manager;

CLLocationManager *locationManager;
CLLocation *currentLocation;
BOOL locationSet = NO;

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    //We set the manager as a means to check reachability. This needs to be done on creation so the AFNetworking library has time to update status.
    NSURL *baseURL = [NSURL URLWithString:@"http://itract.cs.kau.se/"];
    manager = [[AFHTTPRequestOperationManager alloc] initWithBaseURL:baseURL];
    [stopsTableView setDataSource:self];
    [stopsTableView setDelegate:self];
    locationManager = [[CLLocationManager alloc] init];
	// Do any additional setup after loading the view.
}

//Gets all stops in the nearby area. Implementation is done by means of delegation
-(void)getStopsForArea {
    if([CLLocationManager locationServicesEnabled]) {
        locationManager.delegate = self;
        locationManager.desiredAccuracy = kCLLocationAccuracyBest;
        
        [locationManager startUpdatingLocation];
    }
    else {
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Helaas" message:@"Uw locatie kan niet gevonden worden. Om de busstops te kunnen zien moet u deze applicatie rechten in de instellingen van uw apparaat geven om uw locatie op te halen." delegate:self cancelButtonTitle:@"OK" otherButtonTitles:nil];
        [alert show];
    }
}

#pragma mark - CLLocationManagerDelegate

- (void)locationManager:(CLLocationManager *)manager didFailWithError:(NSError *)error {
    UIAlertView *errorAlert = [[UIAlertView alloc]
                               initWithTitle:@"Helaas" message:@"Het is niet gelukt uw locatie op te halen" delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil];
    [errorAlert show];
}

- (void)locationManager:(CLLocationManager *)manager didUpdateToLocation:(CLLocation *)newLocation fromLocation:(CLLocation *)oldLocation {
    currentLocation = newLocation;
    //We check for locationSet to see if the initial location is set. If so it will only refresh the tableview. This is done so the cell can update its distance field.
    if(locationSet) {
        [stopsTableView reloadData];
    }
    else {
        CLLocationCoordinate2D center = newLocation.coordinate;
        MKCoordinateRegion region = MKCoordinateRegionMakeWithDistance(center, 800.0, 800.0);
        CLLocationCoordinate2D northEastCorner, southWestCorner;
        northEastCorner.latitude  = center.latitude  - (region.span.latitudeDelta  / 2.0);
        northEastCorner.longitude = center.longitude - (region.span.longitudeDelta / 2.0);
        southWestCorner.latitude  = center.latitude  + (region.span.latitudeDelta  / 2.0);
        southWestCorner.longitude = center.longitude + (region.span.longitudeDelta / 2.0);
        [self getStopsWithBoxCoordinates:center northEast:northEastCorner southWest:southWestCorner];
    }
}

//Request method to get all stops for a given set of coordinates as a bounding box
-(void)getStopsWithBoxCoordinates:(CLLocationCoordinate2D)center  northEast:(CLLocationCoordinate2D) northEastCorner southWest:(CLLocationCoordinate2D)southWestCorner {
    NSMutableDictionary *mutableParams = [NSMutableDictionary dictionary];
    [mutableParams setObject:[NSNumber numberWithDouble:center.latitude] forKey:@"lat"];
    [mutableParams setObject:[NSNumber numberWithDouble:center.longitude] forKey:@"lon"];
    [mutableParams setObject:[NSNumber numberWithDouble:southWestCorner.latitude] forKey:@"southWestLat"];
    [mutableParams setObject:[NSNumber numberWithDouble:southWestCorner.longitude] forKey:@"southWestLon"];
    [mutableParams setObject:[NSNumber numberWithDouble:northEastCorner.latitude] forKey:@"northEastLat"];
    [mutableParams setObject:[NSNumber numberWithDouble:northEastCorner.longitude] forKey:@"northEastLon"];
    
    NSDictionary *params = [[NSDictionary alloc] initWithDictionary:mutableParams];
    
    //Self object that is block safe to prevent deadlock
    __block NearestStopsViewController *blocksafeSelf = self;
    if ([manager.reachabilityManager isReachable]) {
        //Localmanager is used for time purposes. Can be refactored to use the class scope manager
        AFHTTPRequestOperationManager *localManager = [AFHTTPRequestOperationManager manager];
        localManager.responseSerializer.acceptableContentTypes = [NSSet setWithObject:@"text/html"];
        [localManager GET:@"http://itract.cs.kau.se:8081/proxy/api/transit/stopsInArea" parameters:params success:^(AFHTTPRequestOperation *operation, id responseObject) {
            dispatch_async(dispatch_get_main_queue(), ^{
                NSDictionary *responseDict = [responseObject objectAtIndex:0];
                [blocksafeSelf processStops:[responseDict objectForKey:@"data"]];
            });
        } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
            NSLog(@"Error: %@", error);
        }];
    }
    else {
        dispatch_async(dispatch_get_main_queue(), ^{
            UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Geen connectie" message:@"Er is geen internet connectie actief" delegate:blocksafeSelf cancelButtonTitle:@"OK" otherButtonTitles:nil];
            [alert show];
            locationSet = YES;
        });
    }
}

//Parses given dataarray into array of stops
-(void)processStops:(NSArray *)stops {
    NSMutableArray *mutableStopsList = [NSMutableArray array];
    for(NSDictionary *dict in stops) {
        Busstop *newStop = [[Busstop alloc] init];
        newStop.stopId = [dict objectForKey:@"id"];
        newStop.name = [dict objectForKey:@"name"];
        newStop.agencyId = [dict objectForKey:@"agencyId"];
        NSNumber *lat = [dict objectForKey:@"lat"];
        NSNumber *lon = [dict objectForKey:@"lon"];
        newStop.location = CLLocationCoordinate2DMake([lat doubleValue], [lon doubleValue]);
        [mutableStopsList addObject:newStop];
    }
    stopsList = [[NSArray alloc] initWithArray:mutableStopsList];
    [stopsTableView reloadData];
    //Initial location to yes to signal that we got the location we needed to get the stops
    locationSet = YES;
}

#pragma mark -
#pragma mark TableView
-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return [stopsList count];
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    if(UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPad) {
        return 80;
    }
    else {
        return 60;
    }
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    static NSString *cellIdentifier = @"StopCell";
    
    StopCell *cell = (StopCell *)[tableView dequeueReusableCellWithIdentifier:cellIdentifier];
    cell.stop = [stopsList objectAtIndex:indexPath.row];
    cell.currentLocation = currentLocation;
    [cell updateInfo];
    return cell;
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    TimesForStopViewController *tfvc = [self.storyboard instantiateViewControllerWithIdentifier:@"StopTimesView"];
    tfvc.stop = [stopsList objectAtIndex:indexPath.row];
    [self.navigationController pushViewController:tfvc animated:YES];
}

-(void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex {
    [self.navigationController popViewControllerAnimated:YES];
}

- (IBAction)openMap:(UIButton *)sender {
    StopsMapViewController *smvc = [self.storyboard instantiateViewControllerWithIdentifier:@"BusStopsMapView"];
    smvc.stopsList = stopsList;
    smvc.currentLocation = currentLocation;
    [self.navigationController pushViewController:smvc animated:YES];
}

- (IBAction)popView:(UIButton *)sender {
    [self.navigationController popViewControllerAnimated:YES];
}

-(void)viewWillAppear:(BOOL)animated {
    [self getStopsForArea];
}

-(void)viewWillDisappear:(BOOL)animated {
    [locationManager stopUpdatingLocation];
    locationSet = NO;
}

- (NSUInteger)supportedInterfaceOrientations
{
    return UIInterfaceOrientationMaskPortrait;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
