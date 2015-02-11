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
    public partial class BusTutorial : PhoneApplicationPage
    {
        public BusTutorial()
        {
            InitializeComponent();
        }
        /// <summary>
        /// the same as the main page selection changed, but now the textblock fetched shows the number of the page to go to
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void busmenu_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            if (busmenu.SelectedIndex == -1) return;            
            StackPanel stackpanel = busmenu.SelectedItem as StackPanel;
               
            String page= "";
            foreach (var child in stackpanel.Children)
            {               
                if (child.GetType().ToString().Equals("System.Windows.Controls.TextBlock"))
                {
                    TextBlock textblock = child as TextBlock;
                    page = textblock.Name;
                    break;
                }                
            }
            NavigationService.Navigate(new Uri("/BusTutorialMenu/BusTutorial" + page + ".xaml", UriKind.Relative));
            busmenu.SelectedIndex = -1;
        }
        /// <summary>
        /// The code behind for the tap event of the stackpanel containing the 'home' image
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