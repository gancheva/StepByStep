//
//  StopLocation.m
//  StepByStep
//

#import "StopLocation.h"

@implementation StopLocation
@synthesize name = _name;
@synthesize eventCoordinate = _eventCoordinate;

-(id)initWithName:(NSString *)name subtitle:(NSString *)subtitle coordinate:(CLLocationCoordinate2D)coordinate {
    if((self = [super init])) {
        if([name isKindOfClass:[NSString class]]) {
            self.name = name;
        }
        else {
            self.name = @"Unknown charge";
        }
        self.eventCoordinate = coordinate;
    }
    return self;
}

-(NSString *) title{
    return _name;
}

-(CLLocationCoordinate2D)coordinate {
    return _eventCoordinate;
}
@end
