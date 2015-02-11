//
//  BusTwoViewController.m
//  StepByStep
//

#import "BusTwoViewController.h"
#import "AFNetworking.h"
#import "PromoViewController.h"
#import "AppDelegate.h"
#import "NavigationTemplateViewController.h"

@interface BusTwoViewController ()

@property (strong, nonatomic) IBOutlet UIScrollView *scrollView;
@property (strong, nonatomic) IBOutlet UILabel *titleLabel;
@property (strong, nonatomic) IBOutlet UITextView *subtitleLabel;
@property (strong, nonatomic) IBOutlet UILabel *text1Label;
@property (strong, nonatomic) IBOutlet UILabel *text2Label;
@property (strong, nonatomic) IBOutlet UILabel *text3Title;
@property (strong, nonatomic) IBOutlet UITextView *text3Label;
@property (strong, nonatomic) IBOutlet UILabel *text4Title;
@property (strong, nonatomic) IBOutlet UITextView *text4Label;
@property AFHTTPRequestOperationManager *manager;

@end

@implementation BusTwoViewController
@synthesize scrollView;
@synthesize manager;

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
    NSURL *baseURL = [NSURL URLWithString:@"http://itract.cs.kau.se/"];
    manager = [[AFHTTPRequestOperationManager alloc] initWithBaseURL:baseURL];
    if(UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPad) {
        [scrollView setContentSize:CGSizeMake(768, 880)];
    }
    else {
        [scrollView setContentSize:CGSizeMake(320, 930)];
    }
	// Do any additional setup after loading the view.
}

- (IBAction)playMovie:(UIButton *)sender {
    //Check reachability of backend
    if ([manager.reachabilityManager isReachable]) {
            [self startMovie];
    }
    else {
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Geen connectie" message:@"Er is geen internet connectie actief of de video is niet beschikbaar" delegate:self cancelButtonTitle:@"OK" otherButtonTitles:nil];
        [alert show];
    }
}

//Start playback of movie
-(void)startMovie {
    //Set orientation of parent view to allow landscape
    NavigationTemplateViewController *ntvc = (NavigationTemplateViewController *)[self.parentViewController parentViewController];
    [ntvc setOrientation:UIInterfaceOrientationMaskAllButUpsideDown];
    __block BusTwoViewController *blocksafeSelf = self;
    AFHTTPRequestOperationManager *localManager = [AFHTTPRequestOperationManager manager];
    [localManager GET:@"http://dibbit.nl/itract/api/videosForName/bus_tutorial" parameters:nil success:^(AFHTTPRequestOperation *operation, id responseObject) {
        NSArray *videos = [responseObject objectForKey:@"videos"];
        NSDictionary *video = [videos objectAtIndex:0];
        NSURL *videoURL = [NSURL URLWithString:[video objectForKey:@"url"]];
        dispatch_async(dispatch_get_main_queue(), ^{
            PromoViewController *playerVC = [[PromoViewController alloc] initWithContentURL:videoURL];
            
            [[NSNotificationCenter defaultCenter] removeObserver:playerVC
                                                            name:MPMoviePlayerPlaybackDidFinishNotification
                                                          object:playerVC.moviePlayer];
            
            [[NSNotificationCenter defaultCenter] addObserver:blocksafeSelf
                                                     selector:@selector(movieFinishedCallback:)
                                                         name:MPMoviePlayerPlaybackDidFinishNotification
                                                       object:playerVC.moviePlayer];
            
            [blocksafeSelf.parentViewController presentViewController:playerVC animated:NO completion:nil];
            
            [playerVC.moviePlayer prepareToPlay];
            [playerVC.moviePlayer play];
        });
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        NSLog(@"Error: %@", error);
    }];
}

//Called when the movie finishes. Handle return to parent here
- (void)movieFinishedCallback:(NSNotification*)aNotification
{

    MPMoviePlayerController *moviePlayer = [aNotification object];
    
    [[NSNotificationCenter defaultCenter] removeObserver:self
                                                    name:MPMoviePlayerPlaybackDidFinishNotification
                                                  object:moviePlayer];
    //Reset proper rotation for parent view so it is locked to portrait again
    NavigationTemplateViewController *ntvc = (NavigationTemplateViewController *)[self.parentViewController parentViewController];
    [ntvc setOrientation:UIInterfaceOrientationMaskPortrait];
    [self.parentViewController dismissViewControllerAnimated:NO completion:nil];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
