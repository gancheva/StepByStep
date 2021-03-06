//
//  TestCurrentLocation.m
//  StepByStep
//

#import <XCTest/XCTest.h>
#import <CoreLocation/CoreLocation.h>

@interface TestCurrentLocation : XCTestCase
@property CLLocationManager *lm;
@end

@implementation TestCurrentLocation
@synthesize lm;
- (void)setUp
{
    [super setUp];
    lm = [[CLLocationManager alloc] init];
    lm.desiredAccuracy = kCLLocationAccuracyBest;
    
    [lm startUpdatingLocation];
    // Put setup code here; it will be run once, before the first test case.
}

- (void)tearDown
{
    // Put teardown code here; it will be run once, after the last test case.
    [super tearDown];
}

- (void)testLocationEnabled
{
    XCTAssertTrue([CLLocationManager locationServicesEnabled], @"Location services not enabled for device");
}

@end
