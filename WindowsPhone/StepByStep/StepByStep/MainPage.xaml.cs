using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using Microsoft.Phone.Controls;
using System.IO.IsolatedStorage;

namespace Step_By_Step
{
    public partial class MainPage : PhoneApplicationPage
    {
        // Constructor
        /// <summary>
        /// This is the first constructor of the app. the app checks if it starts for the first time, to launch the tutorial
        /// </summary>
        public MainPage()
        {
           
            InitializeComponent();
            this.Loaded += (s, e) =>
            {
                if (firsttime())
                {
                    NavigationService.Navigate(new Uri("/ApplicationTutorial/ApplicationTutorialPage1.xaml", UriKind.Relative));
                    return;
                }                
            };
           
        }
        /// <summary>
        /// we store in the isolated storage settings if the app is launched for the first time
        /// </summary>
        /// <returns>
        /// boolean true if its the first time, false if it was launched earlier
        /// </returns>
        public Boolean firsttime()
        {
            if (IsolatedStorageSettings.ApplicationSettings.Contains("firsttime"))
            {
                String firsttime = IsolatedStorageSettings.ApplicationSettings["firsttime"] as String;
                return(!firsttime.Equals("no"));
            }
           
            return true;
        }
        /// <summary>
        /// controls the main menu, the menu is build with a listbox, witch fires this event when you touch a item from the list.
        /// It fetches a textblock that shows the page id to go to
        /// </summary>
        /// <param name="sender">the listbox who fired this event</param>
        /// <param name="e">holds info about the event, not used in this case</param>
        private void mainmenu_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            // if the selection is reset, dont take action (stay at the page where you are)
            if (mainmenu.SelectedIndex == -1) return;

                //get the stackpanel witch is selected from the listbox
                StackPanel stackpanel = mainmenu.SelectedItem as StackPanel; 
                //defaults to MainPage, when reading from textblock fales, go to the mainpage (user doesn't notice)
                String page = "MainPage";
                // search components in the stackpanel
                foreach (var child in stackpanel.Children) 
                {   //get the first stackpanel in the list of contents from the parent stackpanel then break
                    if (child.GetType().ToString().Equals("System.Windows.Controls.StackPanel")) 
                    {
                        stackpanel = child as StackPanel; 
                        // search components in the  inner stackpanel
                        foreach (var childofchild in stackpanel.Children) 
                        {   // get the first Textblock and get the name.                     
                            if (childofchild.GetType().ToString().Equals("System.Windows.Controls.TextBlock")) 
                            {
                                TextBlock textblock = childofchild as TextBlock;
                                page = textblock.Name;  
                                break;
                            }       
                        
                        }
                        break;
                    }
                
                }
               
                NavigationService.Navigate(new Uri("/MainMenu/" + page +".xaml", UriKind.Relative)); // go to the page witch was clicked (got this from the textblock)
                mainmenu.SelectedIndex = -1;
        }
        /// <summary>
        /// overrides the default action when you press the back key. this method is present on every page
        /// </summary>        
        protected override void OnBackKeyPress(System.ComponentModel.CancelEventArgs e)
        {
            if (NavigationService.CanGoBack)
            {
                while (NavigationService.RemoveBackEntry() != null)
                {
                    NavigationService.RemoveBackEntry();
                }
            }
        }
     }

}