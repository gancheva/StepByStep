//
//  StopCell.m
//  StepByStep
//

#import "StopCell.h"

@implementation StopCell
@synthesize stopName;
@synthesize stopDistance;
@synthesize stop;
@synthesize currentLocation;

CLLocationManager *locationManager;
CLLocation *stopLocation;

- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        // Initialization code
    }
    return self;
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated
{
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

//Update the cell information with given information
-(void)updateInfo {
    stopName.text = stop.name;
    
    stopLocation = [[CLLocation alloc] initWithLatitude:stop.location.latitude longitude:stop.location.longitude];
    CLLocationDistance distance = [currentLocation distanceFromLocation:stopLocation];
    NSNumber *numberDistance = [NSNumber numberWithFloat:distance];
    stopDistance.text = [NSString stringWithFormat:@"%ld meter", (long)[numberDistance integerValue]];
}

@end
