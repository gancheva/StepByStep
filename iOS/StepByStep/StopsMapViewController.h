//
//  StopsMapViewController.h
//  StepByStep
//

#import <UIKit/UIKit.h>
#import <MapKit/MapKit.h>
#import <CoreLocation/CoreLocation.h>

@interface StopsMapViewController : UIViewController <MKMapViewDelegate>

@property (nonatomic, strong) NSArray *stopsList;
@property (nonatomic, strong) CLLocation *currentLocation;

@end
