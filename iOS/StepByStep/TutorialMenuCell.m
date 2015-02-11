//
//  TutorialMenuCell.m
//  StepByStep
//

#import "TutorialMenuCell.h"

@implementation TutorialMenuCell
@synthesize titleLabel;
@synthesize rowNumberLabel;
@synthesize buttonImageView;

- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        [titleLabel setLineBreakMode:NSLineBreakByWordWrapping];
    }
    return self;
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated
{
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
