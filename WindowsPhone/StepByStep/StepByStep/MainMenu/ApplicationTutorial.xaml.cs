using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;

namespace Step_By_Step.MainMenu
{
    public partial class ApplicationTutorial : PhoneApplicationPage
    {
        public ApplicationTutorial()
        {
            InitializeComponent();
        }

        /// <summary>
        /// Go to home view
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Homebutton_tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            NavigationService.Navigate(new Uri("/MainPage.xaml", UriKind.Relative));
        }

        /// <summary>
        /// Override OnBackKeyPress
        /// </summary>
        /// <param name="e"></param>
        protected override void OnBackKeyPress(System.ComponentModel.CancelEventArgs e)
        {
            e.Cancel = true;
            NavigationService.Navigate(new Uri("/MainPage.xaml", UriKind.Relative));

        }

    }
}