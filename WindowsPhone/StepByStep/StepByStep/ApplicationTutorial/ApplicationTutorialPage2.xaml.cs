using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;
using System.IO.IsolatedStorage;

namespace Step_By_Step.ApplicationTutorial
{
    public partial class ApplicationTutorialPage2 : PhoneApplicationPage
    {
        public ApplicationTutorialPage2()
        {
            InitializeComponent();
            this.Loaded += (s, e) =>
            {
                IsolatedStorageSettings settings = IsolatedStorageSettings.ApplicationSettings;

                if (!settings.Contains("firsttime"))
                {
                    settings.Add("firsttime", "no");
                }
            };
        }

        /// <summary>
        /// Go back
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void back_tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            NavigationService.Navigate(new Uri("/ApplicationTutorial/ApplicationTutorialPage1.xaml", UriKind.Relative));
        }

        /// <summary>
        /// Go to next view
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void next_tap(object sender, System.Windows.Input.GestureEventArgs e)
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
            NavigationService.Navigate(new Uri("/ApplicationTutorial/ApplicationTutorialPage1.xaml", UriKind.Relative));
        }

    }
}