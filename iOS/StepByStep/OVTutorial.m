//
//  OVTutorial.m
//  StepByStep
//

#import "OVTutorial.h"

@implementation OVTutorial
@synthesize titles;
@synthesize views;

-(id) init {
    if (self = [super init])
    {
        [self setTitleArray];
        [self setViewsArray];
    }
    return self;
}

-(void)setTitleArray {
    NSMutableArray *mutableTitles = [NSMutableArray array];
    [mutableTitles addObject:NSLocalizedString(@"ov_menu_info", nil)];
    [mutableTitles addObject:NSLocalizedString(@"ov_menu_buy", nil)];
    [mutableTitles addObject:NSLocalizedString(@"ov_menu_charge", nil)];
    [mutableTitles addObject:NSLocalizedString(@"ov_menu_bus", nil)];
    [mutableTitles addObject:NSLocalizedString(@"ov_menu_train", nil)];
    [mutableTitles addObject:NSLocalizedString(@"ov_menu_forgot", nil)];
    [mutableTitles addObject:NSLocalizedString(@"ov_menu_lost", nil)];
    
    titles = [NSArray arrayWithArray:mutableTitles];
}

-(void)setViewsArray {
    
}

@end
