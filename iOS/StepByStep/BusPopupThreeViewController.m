//
//  BusPopupThreeViewController.m
//  StepByStep
//

#import "BusPopupThreeViewController.h"

@interface BusPopupThreeViewController ()
@property (strong, nonatomic) IBOutlet UILabel *bgLabel;
@property (strong, nonatomic) IBOutlet UIScrollView *scrollView;

@end

@implementation BusPopupThreeViewController
@synthesize bgLabel;
@synthesize scrollView;
@synthesize delegate;

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
        [scrollView setContentSize:CGSizeMake(768, 465)];
    }
    else {
        [scrollView setContentSize:CGSizeMake(280, 465)];
    }
    [bgLabel setAlpha:0.0];
    [scrollView setAlpha:0.0];
    [scrollView.layer setCornerRadius:8.0f];
	// Do any additional setup after loading the view.
}

-(void)viewDidAppear:(BOOL)animated {
    [UIView animateWithDuration:0.2 animations:^{
        [scrollView setAlpha:1.0];
    }];
    [UIView animateWithDuration:0.4 animations:^{
        [bgLabel setAlpha:0.35];
    }];
}

- (IBAction)closePopup:(UIButton *)sender {
    [delegate removePopup];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
