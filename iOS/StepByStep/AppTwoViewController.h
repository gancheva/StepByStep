//
//  AppTwoViewController.h
//  StepByStep
//

#import <UIKit/UIKit.h>
#import "EmbeddableViewController.h"
#import "IPopupViewController.h"

@interface AppTwoViewController : EmbeddableViewController <MBPopupDelegate>

@property (nonatomic, readwrite) BOOL firstTimeSetup;

@end
