//
//  BusTime.h
//  StepByStep
//

#import <Foundation/Foundation.h>

@interface BusTime : NSObject
@property (nonatomic, strong) NSString *agencyName;
@property (nonatomic, strong) NSNumber *delay;
@property (nonatomic, strong) NSString *routeId;
@property (nonatomic, strong) NSNumber *routeShortName;
@property (nonatomic, strong) NSNumber *routeType;
@property (nonatomic, strong) NSNumber *time;
@property (nonatomic, strong) NSString *tripHeadSign;

@end
