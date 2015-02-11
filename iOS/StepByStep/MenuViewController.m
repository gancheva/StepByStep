//
//  MenuViewController.m
//  StepByStep
//

#import "MenuViewController.h"
#import "MenuCell.h"
#import "NavigationTemplateViewController.h"
#import "EmbeddableViewController.h"
#import "ITutorial.h"
#import "BusTutorial.h"
#import "TrainTutorial.h"
#import "OVTutorial.h"
#import "AppTutorial.h"
#import "AppTwoViewController.h"
#import "NearestStopsViewController.h"

@interface MenuViewController ()
    @property (strong, nonatomic) IBOutlet UITableView *menuTableView;
    @property (strong, nonatomic) IBOutlet UILabel *pageTitleUpper;
    @property (strong, nonatomic) IBOutlet UILabel *pageTitleLower;

@end

@implementation MenuViewController
@synthesize menuTableView;
@synthesize pageTitleUpper;
@synthesize pageTitleLower;

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
    [self firstTimeSetup];
    [self localizeView];
    [menuTableView setDataSource:self];
    [menuTableView setDelegate:self];
	// Do any additional setup after loading the view.
}

//Handles firsttime setup to show tutorial on first use
-(void)firstTimeSetup {
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    
    NSNumber *firstTimeSetup = [defaults objectForKey:@"setup"];
    
    if(![firstTimeSetup boolValue]) {
        firstTimeSetup = [NSNumber numberWithBool:YES];
        [defaults setObject:firstTimeSetup forKey:@"setup"];
        
        EmbeddableViewController *displayedViewController = [self.storyboard instantiateViewControllerWithIdentifier:@"TutorialView"];
        [displayedViewController setPopViewType:@"menu"];
        ITutorial *tutorial = [self getTutorialForIndex:4];
        displayedViewController.tutorialInfo = tutorial;
        
        NavigationTemplateViewController *ntvc = [self.storyboard instantiateViewControllerWithIdentifier:@"NavigationTemplateView"];
        ntvc.displayedController = displayedViewController;
        [self.navigationController pushViewController:ntvc animated:YES];
    }
    
}
    
-(void)localizeView {
    pageTitleUpper.text = NSLocalizedString(@"main_title_upper", nil);
    pageTitleLower.text = NSLocalizedString(@"main_title_lower", nil);
}

#pragma mark -
#pragma mark TableView
-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return 5;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    return 130;
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    static NSString *cellIdentifier = @"MenuCell";
    
    MenuCell *cell = (MenuCell *)[tableView dequeueReusableCellWithIdentifier:cellIdentifier];
    [cell.image setImage:[self getImageForIndex:indexPath.row]];
    [cell.description setText:[self getDescriptionForIndex:indexPath.row]];
    return cell;
}

//Handles menu selection. Several exceptions for custom views like busstops
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    if(indexPath.row == 3) {
        NearestStopsViewController *nsvc = [self.storyboard instantiateViewControllerWithIdentifier:@"BusStopsView"];
        [self.navigationController pushViewController:nsvc animated:YES];
    }
    else if(indexPath.row != 4) {
        EmbeddableViewController *displayedViewController = [self.storyboard instantiateViewControllerWithIdentifier:@"TutorialMenuView"];
        [displayedViewController setTitle:[self getDescriptionForIndex:indexPath.row]];
        [displayedViewController setPopViewType:@"menu"];
        ITutorial *tutorial = [self getTutorialForIndex:indexPath.row];
        displayedViewController.tutorialInfo = tutorial;
        
        NavigationTemplateViewController *ntvc = [self.storyboard instantiateViewControllerWithIdentifier:@"NavigationTemplateView"];
        ntvc.displayedController = displayedViewController;
        [self.navigationController pushViewController:ntvc animated:YES];
    }
    else {
        AppTwoViewController *displayedViewController = [self.storyboard instantiateViewControllerWithIdentifier:@"AppTwoView"];
        displayedViewController.firstTimeSetup = NO;
        [displayedViewController setTitle:[self getDescriptionForIndex:indexPath.row]];
        [displayedViewController setPopViewType:@"menu"];
        
        NavigationTemplateViewController *ntvc = [self.storyboard instantiateViewControllerWithIdentifier:@"NavigationTemplateView"];
        ntvc.displayedController = displayedViewController;
        [self.navigationController pushViewController:ntvc animated:YES];
    }
}

//Get the tutorial object for the given menu item
-(ITutorial *)getTutorialForIndex:(NSInteger)index {
    ITutorial *tutorialToReturn;
    switch (index) {
        case 0: {
            tutorialToReturn = [[BusTutorial alloc] init];
            NSMutableArray *mutableViews = [NSMutableArray array];
            [mutableViews addObject:[self.storyboard instantiateViewControllerWithIdentifier:@"BusOneView"]];
            [mutableViews addObject:[self.storyboard instantiateViewControllerWithIdentifier:@"BusTwoView"]];
            [mutableViews addObject:[self.storyboard instantiateViewControllerWithIdentifier:@"BusThreeView"]];
            [mutableViews addObject:[self.storyboard instantiateViewControllerWithIdentifier:@"BusFourView"]];
            [mutableViews addObject:[self.storyboard instantiateViewControllerWithIdentifier:@"BusFiveView"]];
            tutorialToReturn.views = [NSArray arrayWithArray:mutableViews];
            break;
        }
            
        case 1: {
            tutorialToReturn = [[TrainTutorial alloc] init];
            NSMutableArray *mutableViews = [NSMutableArray array];
            [mutableViews addObject:[self.storyboard instantiateViewControllerWithIdentifier:@"TrainOneView"]];
            [mutableViews addObject:[self.storyboard instantiateViewControllerWithIdentifier:@"TrainTwoView"]];
            [mutableViews addObject:[self.storyboard instantiateViewControllerWithIdentifier:@"TrainThreeView"]];
            [mutableViews addObject:[self.storyboard instantiateViewControllerWithIdentifier:@"TrainFourView"]];
            [mutableViews addObject:[self.storyboard instantiateViewControllerWithIdentifier:@"TrainFiveView"]];
            [mutableViews addObject:[self.storyboard instantiateViewControllerWithIdentifier:@"TrainSixView"]];
            tutorialToReturn.views = [NSArray arrayWithArray:mutableViews];
            break;
        }
            
        case 2: {
            tutorialToReturn = [[OVTutorial alloc] init];
            NSMutableArray *mutableViews = [NSMutableArray array];
            [mutableViews addObject:[self.storyboard instantiateViewControllerWithIdentifier:@"OVOneView"]];
            [mutableViews addObject:[self.storyboard instantiateViewControllerWithIdentifier:@"OVTwoView"]];
            [mutableViews addObject:[self.storyboard instantiateViewControllerWithIdentifier:@"OVThreeView"]];
            [mutableViews addObject:[self.storyboard instantiateViewControllerWithIdentifier:@"OVFourView"]];
            [mutableViews addObject:[self.storyboard instantiateViewControllerWithIdentifier:@"OVFiveView"]];
            [mutableViews addObject:[self.storyboard instantiateViewControllerWithIdentifier:@"OVSixView"]];
            [mutableViews addObject:[self.storyboard instantiateViewControllerWithIdentifier:@"OVSevenView"]];
            tutorialToReturn.views = [NSArray arrayWithArray:mutableViews];
            break;
        }
            
        case 3: {
            tutorialToReturn = [[AppTutorial alloc] init];
            break;
        }
            
        case 4: {
            tutorialToReturn = [[AppTutorial alloc] init];
            NSMutableArray *mutableViews = [NSMutableArray array];
            [mutableViews addObject:[self.storyboard instantiateViewControllerWithIdentifier:@"AppOneView"]];
            AppTwoViewController *atvc = [self.storyboard instantiateViewControllerWithIdentifier:@"AppTwoView"];
            atvc.firstTimeSetup = YES;
            [mutableViews addObject:atvc];
            tutorialToReturn.views = [NSArray arrayWithArray:mutableViews];
            break;
        }
            
        default:
            tutorialToReturn = [[ITutorial alloc] init];
            break;
    }
    return tutorialToReturn;
}

//Returns the image to use for the menu item
-(UIImage *) getImageForIndex:(NSInteger)index {
    UIImage *imageToReturn;
    switch (index) {
        case 0:
            imageToReturn = [UIImage imageNamed:@"mainmenu_bus"];
            break;
            
        case 1:
            imageToReturn = [UIImage imageNamed:@"mainmenu_train"];
            break;
            
        case 2:
            imageToReturn = [UIImage imageNamed:@"mainmenu_ov"];
            break;
            
        case 3:
            imageToReturn = [UIImage imageNamed:@"mainmenu_stops"];
            break;
            
        case 4:
            imageToReturn = [UIImage imageNamed:@"mainmenu_howto"];
            break;
            
        default:
            imageToReturn = [UIImage imageNamed:@"test"];
            break;
    }
    return imageToReturn;
}

//Returns the menu items name
-(NSString *)getDescriptionForIndex:(NSInteger)index {
    switch (index) {
        case 0:
            return NSLocalizedString(@"main_menu_bus", nil);
            break;
            
        case 1:
            return NSLocalizedString(@"main_menu_train", nil);
            break;
            
        case 2:
            return NSLocalizedString(@"main_menu_ov", nil);
            break;
        
        case 3:
            return NSLocalizedString(@"main_menu_stops", nil);
            break;
            
        case 4:
            return NSLocalizedString(@"main_menu_app", nil);
            break;
            
        default:
            return @"No text specified";
            break;
    }
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
