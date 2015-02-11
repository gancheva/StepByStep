//
//  BusTutorial.m
//  StepByStep
//

#import "BusTutorial.h"

@implementation BusTutorial
@synthesize titles;
@synthesize views;

-(id) init {
    if (self = [super init])
    {
        [self setTitleArray];
    }
    return self;
}

-(void)setTitleArray {
    NSMutableArray *mutableTitles = [NSMutableArray array];
    [mutableTitles addObject:NSLocalizedString(@"bus_menu_plan", nil)];
    [mutableTitles addObject:NSLocalizedString(@"bus_menu_pay", nil)];
    [mutableTitles addObject:NSLocalizedString(@"bus_menu_stop", nil)];
    [mutableTitles addObject:NSLocalizedString(@"bus_menu_trip", nil)];
    [mutableTitles addObject:NSLocalizedString(@"bus_menu_travelback", nil)];
    
    titles = [NSArray arrayWithArray:mutableTitles];
}

@end
