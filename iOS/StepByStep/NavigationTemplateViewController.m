//
//  NavigationTemplateViewController.m
//  StepByStep
//
//  Encapsulating viewcontroller to look like a custom uinavigationcontroller implementation.
//  This is done because uinavigationcontroller doesnt support a bar with more than 40pt height
//

#import "NavigationTemplateViewController.h"

@interface NavigationTemplateViewController ()
@property (weak, nonatomic) IBOutlet UIButton *backButton;
@property (weak, nonatomic) IBOutlet UIView *displayedView;
@property (strong, nonatomic) EmbeddableViewController *currentViewController;


@end

@implementation NavigationTemplateViewController
@synthesize displayedController;
@synthesize backButton;
@synthesize displayedView;
@synthesize displayedTitle;
@synthesize currentViewController;
@synthesize orientation;

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
    orientation = UIInterfaceOrientationMaskPortrait;
    UIImage *buttonImage;
    if([displayedController.popViewType isEqualToString:@"menu"]) {
        buttonImage = [UIImage imageNamed:@"btn_back_menu"];
    }
    else if([displayedController.popViewType isEqualToString:@"steps"]) {
        buttonImage = [UIImage imageNamed:@"btn_back_steps"];
    }
    
    [backButton setImage:buttonImage forState:UIControlStateNormal];
    [backButton setImage:buttonImage forState:UIControlStateSelected];
    [backButton setImage:buttonImage forState:UIControlStateHighlighted];
    [backButton setContentHorizontalAlignment:UIControlContentHorizontalAlignmentFill];
    [backButton setContentVerticalAlignment:UIControlContentVerticalAlignmentFill];
    
    if(![[self displayedController] isEqual:NULL]) {
        [currentViewController removeFromParentViewController];
        [self setCurrentViewController:displayedController];
        [self addChildViewController:currentViewController];
        
        displayedController.view.frame = displayedView.frame;
        [self.view addSubview:displayedController.view];
        [self.displayedView removeFromSuperview];
        [self.view setNeedsDisplay];
        
        [self.displayedTitle setText:displayedController.title];
    }
	// Do any additional setup after loading the view.
}

-(void)refreshTitle {
    displayedTitle.text = displayedController.title;
}

-(IBAction)popView:(UIButton *)sender {
    [self.navigationController popViewControllerAnimated:YES];
}

- (NSUInteger)supportedInterfaceOrientations
{
    return orientation;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
