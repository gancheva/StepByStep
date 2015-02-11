//
//  IPopupViewController.h
//  StepByStep
//

#import <UIKit/UIKit.h>
#import <Foundation/Foundation.h>

@protocol MBPopupDelegate <NSObject>
@required
- (void) removePopup;
@end

@interface IPopupViewController : UIViewController

{
    id <MBPopupDelegate> _delegate;
    
}
@property (nonatomic,strong) id delegate;

@end
