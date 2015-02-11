//
//  TrainOneViewController.m
//  StepByStep
//

#import "TrainOneViewController.h"

@interface TrainOneViewController ()
@property (strong, nonatomic) IBOutlet UIScrollView *scrollView;
@property (strong, nonatomic) IBOutlet UILabel *titleLabel;
@property (strong, nonatomic) IBOutlet UILabel *subtitleLabel;
@property (strong, nonatomic) IBOutlet UITextView *text1TextView;
@property (strong, nonatomic) IBOutlet UITextView *text2TextView;
@property (strong, nonatomic) IBOutlet UILabel *text3Label;
@property (strong, nonatomic) IBOutlet UILabel *text4Label;
@property (strong, nonatomic) IBOutlet UILabel *text5Label;
@property (strong, nonatomic) IBOutlet UITextView *text6TextView;
@property (strong, nonatomic) IBOutlet UIButton *text3Button;
@property (strong, nonatomic) IBOutlet UIButton *text4Button;
@property (strong, nonatomic) IBOutlet UIButton *text5Button;

@end

@implementation TrainOneViewController
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
        [scrollView setContentSize:CGSizeMake(768, 510)];
    }
    else {
        [scrollView setContentSize:CGSizeMake(320, 500)];
    }
	// Do any additional setup after loading the view.
}

- (IBAction)popupButton:(UIButton *)sender {
    switch (sender.tag) {
        case 0: {
            pvc = [self.storyboard instantiateViewControllerWithIdentifier:@"TrainDefaultPopup"];
            break;
        }
            
        case 1: {
            pvc = [self.storyboard instantiateViewControllerWithIdentifier:@"TrainArrivaPopup"];
            break;
        }
            
        case 2: {
            pvc = [self.storyboard instantiateViewControllerWithIdentifier:@"TrainNSPopup"];
            break;
        }
            
        default:
            break;
    }
    [pvc setDelegate:self];
    [self.parentViewController.parentViewController.view addSubview:pvc.view];
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
