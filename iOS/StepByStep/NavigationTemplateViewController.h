//
//  NavigationTemplateViewController.h
//  StepByStep
//

#import <UIKit/UIKit.h>
#import "EmbeddableViewController.h"

@interface NavigationTemplateViewController : UIViewController

@property (nonatomic, strong) EmbeddableViewController *displayedController;
@property (weak, nonatomic) IBOutlet UILabel *displayedTitle;
@property (nonatomic, readwrite) NSUInteger orientation;

-(void)refreshTitle;

@end
