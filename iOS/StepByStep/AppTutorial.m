//
//  AppTutorial.m
//  StepByStep
//

#import "AppTutorial.h"

@implementation AppTutorial
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
    [mutableTitles addObject:NSLocalizedString(@"first_screen_title", nil)];
    [mutableTitles addObject:NSLocalizedString(@"first_screen_title", nil)];
    
    titles = [NSArray arrayWithArray:mutableTitles];
}

-(void)setViewsArray {
}

@end
