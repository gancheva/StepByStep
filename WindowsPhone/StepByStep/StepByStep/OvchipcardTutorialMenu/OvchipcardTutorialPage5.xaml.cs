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
    public partial class OvchipcardTutorialPage5 : PhoneApplicationPage
    {
        public OvchipcardTutorialPage5()
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
        /// Go back
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void back_tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            NavigationService.Navigate(new Uri("/OvchipcardTutorialMenu/OvchipcardTutorialPage4.xaml", UriKind.Relative));
        }

        /// <summary>
        /// Go to next view
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void next_tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            NavigationService.Navigate(new Uri("/OvchipcardTutorialMenu/OvchipcardTutorialPage6.xaml", UriKind.Relative));
        }
    }
}