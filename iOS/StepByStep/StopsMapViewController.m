//
//  StopsMapViewController.m
//  StepByStep
//

#import "StopsMapViewController.h"
#import "Busstop.h"
#import "StopLocation.h"

@interface StopsMapViewController ()
@property (strong, nonatomic) IBOutlet MKMapView *stopsMap;

@end

@implementation StopsMapViewController
@synthesize stopsList;
@synthesize stopsMap;
@synthesize currentLocation;

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
    stopsMap.delegate = self;
    [self plotStopPositions];
	// Do any additional setup after loading the view.
}

/*-(MKOverlayView *)mapView:(MKMapView *)mapView viewForOverlay:(id<MKOverlay>)overlay {
 MapOverlay *mapOverlay = (MapOverlay *)overlay;
 MapOverlayView *mapOverlayView = [[MapOverlayView alloc] initWithOverlay:mapOverlay];
 mapOverlayView.imageName = mapOverlay.imageName;
 
 return mapOverlayView;
 }*/

//Add all given stops on the map
-(void)plotStopPositions {
    
    for(Busstop *location in stopsList) {
        StopLocation *annotation = [[StopLocation alloc] initWithName:location.name subtitle:nil coordinate:location.location];
        [stopsMap addAnnotation:annotation];
    }
}

//Add image to given stop. Identifier makes annotation reusable to lighten resource use and creation times
-(MKAnnotationView *)mapView:(MKMapView *)mapView viewForAnnotation:(id<MKAnnotation>)annotation {
    static NSString *identifier = @"StopLocation";
    if([annotation isKindOfClass:[StopLocation class]]) {
        MKAnnotationView *annotationView = (MKAnnotationView *) [self.stopsMap dequeueReusableAnnotationViewWithIdentifier:identifier];
        if(annotationView == nil) {
            annotationView = [[MKAnnotationView alloc] initWithAnnotation:annotation reuseIdentifier:identifier];
            annotationView.enabled = YES;
            annotationView.canShowCallout = YES;
            annotationView.image = [UIImage imageNamed:@"busstop_marker"];
        }
        else {
            annotationView.annotation = annotation;
        }
        
        return annotationView;
    }
    
    return nil;
}

- (IBAction)popView:(UIButton *)sender {
    [self.navigationController popViewControllerAnimated:YES];
}

//Zoom the map to the users location when it becomes visible
-(void) viewWillAppear:(BOOL)animated {
    MKCoordinateRegion region = MKCoordinateRegionMakeWithDistance(currentLocation.coordinate, 800, 800);
    [stopsMap setRegion:region animated:YES];
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
