using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;

namespace Step_By_Step.ApplicationTutorial
{
    public partial class ApplicationTutorialPage1 : PhoneApplicationPage
    {
        public ApplicationTutorialPage1()
        {
            InitializeComponent();
        }

        /// <summary>
        /// Go to the next view
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void next_tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            NavigationService.Navigate(new Uri("/ApplicationTutorial/ApplicationTutorialPage2.xaml", UriKind.Relative));
        }

        /// <summary>
        /// Override OnBackKeyPress
        /// </summary>
        /// <param name="e"></param>
        protected override void OnBackKeyPress(System.ComponentModel.CancelEventArgs e)
        {
            e.Cancel = true;
           
        }
    }
}