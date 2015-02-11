//
//  EmbeddableViewController.m
//  StepByStep
//
//  Other classes can choose this as their class type to be able to be used by navigationtemplate
//

#import "EmbeddableViewController.h"

@interface EmbeddableViewController ()

@end

@implementation EmbeddableViewController
@synthesize popViewType = _popViewType;

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
	// Do any additional setup after loading the view.
}

-(void)setPopViewType:(NSString *)popViewType {
    NSArray *validPopOptions = @[@"menu", @"steps"];
    for(NSString *option in validPopOptions) {
        if([popViewType isEqualToString:option]) {
            _popViewType = popViewType;
            return;
        }
    }
    _popViewType = [validPopOptions objectAtIndex:0];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
