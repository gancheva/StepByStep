﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;

namespace Step_By_Step.TrainTutorialMenu
{
    public partial class TrainTutorialPage6 : PhoneApplicationPage
    {
        public TrainTutorialPage6()
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
            NavigationService.Navigate(new Uri("/MainMenu/TrainTutorial.xaml", UriKind.Relative));
        }

        /// <summary>
        /// Go back
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void back_tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            NavigationService.Navigate(new Uri("/TrainTutorialMenu/TrainTutorialPage5.xaml", UriKind.Relative));
        }

        /// <summary>
        /// Go to main menu
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void go_to_main_menu_tap(object sender, System.Windows.Input.GestureEventArgs e)
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
            NavigationService.Navigate(new Uri("/MainMenu/TrainTutorial.xaml", UriKind.Relative));
        }
    }
}