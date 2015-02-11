//
//  StopLocation.h
//  StepByStep
//

#import <Foundation/Foundation.h>
#import <MapKit/MapKit.h>

@interface StopLocation : NSObject <MKAnnotation>

@property (nonatomic, strong) NSString *name;
@property (nonatomic, assign) CLLocationCoordinate2D eventCoordinate;

-(id)initWithName:(NSString*)name subtitle:(NSString*)subtitle coordinate:(CLLocationCoordinate2D)coordinate;

@end
