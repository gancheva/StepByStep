using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;

namespace Step_By_Step
{
    public partial class TrainTutorial : PhoneApplicationPage
    {
        public TrainTutorial()
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
        /// Method to handle selection change
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void trainmenu_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            if (trainmenu.SelectedIndex == -1) return;            
            StackPanel stackpanel = trainmenu.SelectedItem as StackPanel;

            String page = "";
            foreach (var child in stackpanel.Children)
            {
                if (child.GetType().ToString().Equals("System.Windows.Controls.TextBlock"))
                {
                    TextBlock textblock = child as TextBlock;
                    page = textblock.Name;
                    break;
                }
            }
            NavigationService.Navigate(new Uri("/TrainTutorialMenu/TrainTutorial" + page + ".xaml", UriKind.Relative));
            trainmenu.SelectedIndex = -1;
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