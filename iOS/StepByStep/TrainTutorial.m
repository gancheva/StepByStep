//
//  TrainTutorial.m
//  StepByStep
//

#import "TrainTutorial.h"

@implementation TrainTutorial
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
    [mutableTitles addObject:NSLocalizedString(@"train_menu_plan", nil)];
    [mutableTitles addObject:NSLocalizedString(@"train_menu_station", nil)];
    [mutableTitles addObject:NSLocalizedString(@"train_menu_ticket", nil)];
    [mutableTitles addObject:NSLocalizedString(@"train_menu_trip", nil)];
    [mutableTitles addObject:NSLocalizedString(@"train_menu_checkout", nil)];
    [mutableTitles addObject:NSLocalizedString(@"train_menu_travelback", nil)];
    
    titles = [NSArray arrayWithArray:mutableTitles];
}

-(void)setViewsArray {
    
}

@end
