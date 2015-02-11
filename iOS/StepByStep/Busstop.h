//
//  Busstop.h
//  StepByStep
//

#import <Foundation/Foundation.h>
#import <CoreLocation/CoreLocation.h>

@interface Busstop : NSObject

@property (nonatomic, strong) NSNumber *stopId;
@property (nonatomic, strong) NSString *name;
@property (nonatomic, strong) NSNumber *agencyId;
@property (nonatomic, readwrite) CLLocationCoordinate2D location;

@end
