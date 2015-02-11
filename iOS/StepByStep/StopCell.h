//
//  StopCell.h
//  StepByStep
//

#import <UIKit/UIKit.h>
#import "Busstop.h"
#import <CoreLocation/CoreLocation.h>

@interface StopCell : UITableViewCell <CLLocationManagerDelegate>
@property (strong, nonatomic) IBOutlet UILabel *stopName;
@property (strong, nonatomic) IBOutlet UILabel *stopDistance;
@property (strong, nonatomic) Busstop *stop;
@property (strong, nonatomic) CLLocation *currentLocation;

-(void)updateInfo;
@end
