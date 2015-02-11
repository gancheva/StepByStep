//
//  TimesForStopViewController.h
//  StepByStep
//

#import <UIKit/UIKit.h>
#import "Busstop.h"

@interface TimesForStopViewController : UIViewController <UITableViewDataSource, UITableViewDelegate>
@property (strong, nonatomic) Busstop *stop;


@end
