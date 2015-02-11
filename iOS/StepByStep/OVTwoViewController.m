//
//  OVTwoViewController.m
//  StepByStep
//

#import "OVTwoViewController.h"

@interface OVTwoViewController ()
@property (strong, nonatomic) IBOutlet UIScrollView *scrollView;
@property (strong, nonatomic) IBOutlet UILabel *titleLabel;
@property (strong, nonatomic) IBOutlet UITextView *text1TextView;
@property (strong, nonatomic) IBOutlet UILabel *text2Title;
@property (strong, nonatomic) IBOutlet UITextView *text2TextView;
@property (strong, nonatomic) IBOutlet UILabel *text3Title;
@property (strong, nonatomic) IBOutlet UITextView *text3TextView;

@end

@implementation OVTwoViewController
@synthesize scrollView;

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
        [scrollView setContentSize:CGSizeMake(768, 510)];
    }
    else {
        [scrollView setContentSize:CGSizeMake(320, 520)];
    }
	// Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
