using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;

namespace Step_By_Step.OvchipcardTutorialMenu
{
    public partial class OvchipcardTutorialPage1 : PhoneApplicationPage
    {
        public OvchipcardTutorialPage1()
        {
            InitializeComponent();
        }

        /// <summary>
        /// Go to steps view 
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Steps_Tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            NavigationService.Navigate(new Uri("/MainMenu/OvchipcardTutorial.xaml", UriKind.Relative));
        }

        /// <summary>
        /// go to next view
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void next_tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            NavigationService.Navigate(new Uri("/OvchipcardTutorialMenu/OvchipcardTutorialPage2.xaml", UriKind.Relative));
        }
    }
}