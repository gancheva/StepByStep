//
//  NavigationTemplateTest.m
//  StepByStep
//

#import <XCTest/XCTest.h>
#import "NavigationTemplateViewController.h"
#import "EmbeddableViewController.h"

@interface NavigationTemplateTest : XCTestCase
@property NavigationTemplateViewController *ntvc;
@end

@implementation NavigationTemplateTest
@synthesize ntvc;

NSString *menu = @"menu";

- (void)setUp
{
    [super setUp];
    ntvc = [[NavigationTemplateViewController alloc] init];
    EmbeddableViewController *ebvc = [[EmbeddableViewController alloc] init];
    [ebvc setPopViewType:menu];
    ntvc.displayedController = ebvc;
    // Put setup code here; it will be run once, before the first test case.
}

- (void)tearDown
{
    // Put teardown code here; it will be run once, after the last test case.
    ntvc = nil;
    [super tearDown];
}

- (void)testSetDisplayController
{
    XCTAssertNotNil(ntvc.displayedController, @"Setting displayController failed");
}

-(void)testSetPopViewType {
    XCTAssertEqual(ntvc.displayedController.popViewType, menu, @"Setting default popviewtype failed");
}

@end
