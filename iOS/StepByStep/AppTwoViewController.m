//
//  AppTwoViewController.m
//  StepByStep
//

#import "AppTwoViewController.h"

@interface AppTwoViewController ()
@property (strong, nonatomic) IBOutlet UIScrollView *scrollView;
@property (strong, nonatomic) IBOutlet UITextView *text1TextView;
@property (strong, nonatomic) IBOutlet UITextView *text2TextView;
@property (strong, nonatomic) IBOutlet UITextView *text3TextView;
@property (strong, nonatomic) IBOutlet UITextView *text4TextView;
@property (strong, nonatomic) IBOutlet UITextView *text5TextView;

@end

@implementation AppTwoViewController
@synthesize firstTimeSetup;
@synthesize scrollView;

IPopupViewController *pvc;

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
    if(UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPad) {
        [scrollView setContentSize:CGSizeMake(768, 580)];
    }
    else {
        [scrollView setContentSize:CGSizeMake(320, 480)];
    }
	// Do any additional setup after loading the view.
}

- (IBAction)popupButton:(UIButton *)sender {
    pvc = [self.storyboard instantiateViewControllerWithIdentifier:@"TutorialPopup"];
    [pvc setDelegate:self];
    if(firstTimeSetup) {
        [self.parentViewController.parentViewController.view addSubview:pvc.view];
    }
    else {
        [self.parentViewController.view addSubview:pvc.view];
    }
}

-(void)removePopup {
    [pvc.view removeFromSuperview];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
