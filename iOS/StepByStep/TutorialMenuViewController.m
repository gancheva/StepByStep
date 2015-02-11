//
//  TutorialMenuViewController.m
//  StepByStep
//

#import "TutorialMenuViewController.h"
#import "NavigationTemplateViewController.h"
#import "TutorialMenuCell.h"

@interface TutorialMenuViewController ()
@property (strong, nonatomic) IBOutlet UITableView *menuTableView;

@end

@implementation TutorialMenuViewController
@synthesize menuTableView;
@synthesize tutorialInfo;

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
    [menuTableView setDataSource:self];
    [menuTableView setDelegate:self];
	// Do any additional setup after loading the view.
}

#pragma mark -
#pragma mark TableView

-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return [tutorialInfo.titles count];
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    if(UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPad) {
        return 90;
    }
    else {
        return 70;
    }
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    static NSString *cellIdentifier = @"TutorialMenuCell";
    
    TutorialMenuCell *cell = (TutorialMenuCell *)[tableView dequeueReusableCellWithIdentifier:cellIdentifier];
    
    cell.rowNumberLabel.text = [NSString stringWithFormat:@"%ld", (long)(indexPath.row + 1)];
    cell.titleLabel.text = [NSString stringWithFormat:@"%@", [tutorialInfo.titles objectAtIndex:indexPath.row]];
    
    cell.buttonImageView.image = [UIImage imageNamed:@"tutorialmenu_arrow_icon"];
    [cell.buttonImageView setContentMode:UIViewContentModeScaleAspectFit];
    
    return cell;
}

//Fix to clear excess empty cells that ios standard add to the rest of the uitableview space
- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section {
    // This will create a "invisible" footer
    return 0.01f;
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    NavigationTemplateViewController *ntvc = [self.storyboard instantiateViewControllerWithIdentifier:@"NavigationTemplateView"];
    
    EmbeddableViewController *displayedViewController = [self.storyboard instantiateViewControllerWithIdentifier:@"TutorialView"];
    displayedViewController.tutorialInfo = tutorialInfo;
    displayedViewController.startPageIndex = indexPath.row;
    [displayedViewController setPopViewType:@"steps"];
    
    ntvc.displayedController = displayedViewController;
    [self.parentViewController.navigationController pushViewController:ntvc animated:YES];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
