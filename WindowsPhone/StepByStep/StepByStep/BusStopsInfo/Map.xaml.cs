using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;
using Microsoft.Phone.Controls.Maps;
using System.Device.Location;
using System.Windows.Input;
using System.Windows.Controls.Primitives;
using System.Windows.Media.Imaging;
using System.Windows.Media;

namespace Step_By_Step.BusStopsInfo
{
    public partial class Map : PhoneApplicationPage
    {
        Popup popup;
        public Map()
        {
            InitializeComponent();
            map.CredentialsProvider = new ApplicationIdCredentialsProvider("AqVTh9UOLB8I8-ihKpPrioQxmJ01HRuBqVN85MGZNzZeFCEJnK0g4uYpXBN5Vhoh");
            popup = new Popup();
        }

        /// <summary>
        /// Overrides the method OnNavigatedTo. It is executed when the Map.xaml view is called.
        /// </summary>
        /// <param name="e"></param>
        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            string latitude = string.Empty;
            string longitude = string.Empty;
            Pushpin pin = new Pushpin();
            if (NavigationContext.QueryString.TryGetValue("latitude", out latitude) & NavigationContext.QueryString.TryGetValue("longitude", out longitude))
            {
                map.Center = new GeoCoordinate(Convert.ToDouble(latitude), Convert.ToDouble(longitude));
                pin.Location = new GeoCoordinate(Convert.ToDouble(latitude), Convert.ToDouble(longitude));
            }
            map.ZoomLevel = 16.0;
            pin.Template = (ControlTemplate)(this.Resources["PushpinControlTemplate2"]);
            map.Children.Add(pin);
            
            Step_By_Step.MainMenu.BusStop[] busStopsPins = Step_By_Step.MainMenu.BushaltesInfo.pins;
            if (busStopsPins != null && busStopsPins.Length > 0) {
                foreach (Step_By_Step.MainMenu.BusStop current in busStopsPins)
                {
                    pin = new Pushpin();
                    pin.Location = new GeoCoordinate(current.lat, current.lon);
                    pin.Name = current.name + "; " + current.agencyId;
                    pin.Tap += new EventHandler<GestureEventArgs>(Pushpin_Tap);
                    pin.Template = (ControlTemplate)(this.Resources["PushpinControlTemplate1"]);
                    map.Children.Add(pin);
                }
            }
        }

        /// <summary>
        /// Method called when a pushpin is selected 
        /// </summary>
        /// <param name="e"></param>
        /// <param name="sender"></param>
        private void Pushpin_Tap(object sender, GestureEventArgs e)
        {
            //Pushpin pushpin = sender as Pushpin;
            DimmerControl.Visibility = System.Windows.Visibility.Visible;
            Display_Popup(sender as Pushpin);           
            // to stop the event from going to the parent map control
            e.Handled = true;
        }

        /// <summary>
        /// Display popup
        /// </summary>
        /// <param name="pushpin"></param>
        private void Display_Popup(Pushpin pushpin)
        {
            StackPanel panel = Popup_Panel(pushpin);
            popup.Child = panel;
            popup.VerticalOffset = 50;
            popup.HorizontalOffset = 10;
            popup.IsOpen = true;
        }

        /// <summary>
        /// Create a popup for the given pushpin
        /// </summary>
        /// <param name="pushpin"></param>
        private StackPanel Popup_Panel(Pushpin pushpin)
        {
            StackPanel panel = new StackPanel();
            panel.Background = new SolidColorBrush(Colors.White);
            panel.Orientation = System.Windows.Controls.Orientation.Horizontal;

            StackPanel text_panel = new StackPanel();
            text_panel.VerticalAlignment = System.Windows.VerticalAlignment.Center;
            text_panel.HorizontalAlignment = System.Windows.HorizontalAlignment.Left;
            text_panel.Margin = new Thickness(10, 0, 0, 0);
            TextBlock text = new TextBlock();
            text.TextWrapping = TextWrapping.Wrap;
            text.Text = pushpin.Name;
            text.Foreground = new SolidColorBrush(Colors.Black);
            text.FontSize = 22;
            text_panel.Children.Add(text);
            panel.Children.Add(text_panel);

            Image img = new Image();
            img.VerticalAlignment = System.Windows.VerticalAlignment.Center;
            img.HorizontalAlignment = System.Windows.HorizontalAlignment.Right;
            img.Margin = new Thickness(20, 0, 10, 0);
            img.Tap += DimmerControl_Tap;
            img.Height = 40;
            img.Source = new BitmapImage(new Uri("Assets/Images/exit_button.png", UriKind.Relative));
            panel.Children.Add(img);

            return panel;
        }

        /// <summary>
        /// Overrides the OnBackKeyPress method of Windows Phone
        /// </summary>
        /// <param name="e"></param>
        protected override void OnBackKeyPress(System.ComponentModel.CancelEventArgs e)
        {
            e.Cancel = true;
            popup.IsOpen = false;
            NavigationService.Navigate(new Uri("/MainMenu/BushaltesInfo.xaml", UriKind.Relative));
        }

        /// <summary>
        /// Dimmer control 
        /// </summary>
        /// <param name="e"></param>
        /// <param name="sender"></param>
        private void DimmerControl_Tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            popup.IsOpen = false;
            DimmerControl.Visibility = System.Windows.Visibility.Collapsed;
        }

        /// <summary>
        /// Return to the bus stops view
        /// </summary>
        /// <param name="e"></param>
        /// <param name="sender"></param>
        private void Steps_Tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            NavigationService.Navigate(new Uri("/MainMenu/BushaltesInfo.xaml", UriKind.Relative));
        }

    }
}