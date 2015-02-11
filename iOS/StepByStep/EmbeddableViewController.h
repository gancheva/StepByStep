//
//  EmbeddableViewController.h
//  StepByStep
//

#import <UIKit/UIKit.h>
#import "ITutorial.h"

@interface EmbeddableViewController : UIViewController
//The pop view type of the view used when choosing a back button for the view. Can be either "menu" or "steps"
@property (nonatomic, strong) NSString *popViewType;
@property (nonatomic, strong) ITutorial *tutorialInfo;
@property (readwrite) NSInteger startPageIndex;
@end
