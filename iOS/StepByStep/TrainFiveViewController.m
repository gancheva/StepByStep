//
//  TrainFiveViewController.m
//  StepByStep
//

#import "TrainFiveViewController.h"

@interface TrainFiveViewController ()
@property (strong, nonatomic) IBOutlet UIScrollView *scrollView;
@property (strong, nonatomic) IBOutlet UILabel *titleLabel;
@property (strong, nonatomic) IBOutlet UITextView *text1TextView;

@end

@implementation TrainFiveViewController
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
        [scrollView setContentSize:CGSizeMake(768, 220)];
    }
    else {
        [scrollView setContentSize:CGSizeMake(320, 340)];
    }
	// Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
