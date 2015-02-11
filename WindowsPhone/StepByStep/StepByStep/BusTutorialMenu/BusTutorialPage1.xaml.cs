using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;
using System.Windows.Controls.Primitives;
using System.Windows.Media.Imaging;
using System.Windows.Media;


namespace Step_By_Step
{
    public partial class BusTutorialPage1 : PhoneApplicationPage
    {
        Popup popup;
        public BusTutorialPage1()
        {
            InitializeComponent();
            popup = new Popup();
        }

        /// <summary>
        /// Go to steps view
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Steps_Tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            NavigationService.Navigate(new Uri("/MainMenu/BusTutorial.xaml", UriKind.Relative));
        }

        /// <summary>
        /// Go to next view
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void next_tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            NavigationService.Navigate(new Uri("/BusTutorialMenu/BusTutorialPage2.xaml", UriKind.Relative));
        }

        /// <summary>
        /// Dimmer control and display popup
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void internet_planning_tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
           DimmerControl.Visibility = System.Windows.Visibility.Visible;
           Display_Internet_Popup();
        }

        /// <summary>
        /// Dimmer control and display popup
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void telephone_planning_tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            DimmerControl.Visibility = System.Windows.Visibility.Visible;
            Display_Telephone_Popup();
        }

        /// <summary>
        /// Dimmer control and display popup
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void bus_stop_planning_tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            DimmerControl.Visibility = System.Windows.Visibility.Visible;
            Display_Busstop_Popup();
        }

        /// <summary>
        /// Override OnBackKeyPress method
        /// </summary>
        /// <param name="e"></param>
        protected override void OnBackKeyPress(System.ComponentModel.CancelEventArgs e)
        {
            e.Cancel = true;
            popup.IsOpen = false;
            NavigationService.Navigate(new Uri("/MainMenu/BusTutorial.xaml", UriKind.Relative));
        }

        /// <summary>
        /// Dimmer control 
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void DimmerControl_Tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            popup.IsOpen = false;
            DimmerControl.Visibility = System.Windows.Visibility.Collapsed;
        }
                       
        /// <summary>
        /// Display popup
        /// </summary>
        private void Display_Internet_Popup()
        {        
            StackPanel outer_panel = Outer_Popup_Panel();
            outer_panel.Children.Add(Via_Internet_Popup_Content());
            popup.Child = outer_panel;

            popup.VerticalOffset = 20;
            popup.HorizontalOffset = 20;

            popup.IsOpen = true;

        }

        /// <summary>
        /// Display popup
        /// </summary>
        private void Display_Telephone_Popup()
        {
            StackPanel outer_panel = Outer_Popup_Panel();
            outer_panel.Children.Add(Telephone_Popup_Content());
            popup.Child = outer_panel;

            popup.VerticalOffset = 20;
            popup.HorizontalOffset = 20;

            popup.IsOpen = true;
        }

        /// <summary>
        /// Display popup
        /// </summary>
        private void Display_Busstop_Popup()
        {
            StackPanel outer_panel = Outer_Popup_Panel();
            outer_panel.Children.Add(Busstop_Popup_Content());
            popup.Child = outer_panel;

            popup.VerticalOffset = 20;
            popup.HorizontalOffset = 20;

            popup.IsOpen = true;
        }

        /// <summary>
        /// Create popup
        /// </summary>
        /// <returns></returns>
        private StackPanel Outer_Popup_Panel()
        {
            StackPanel outer_panel = new StackPanel();
            outer_panel.Height = 600;
            outer_panel.Width = 440;
            outer_panel.Background = new SolidColorBrush(Colors.White);
            outer_panel.Orientation = System.Windows.Controls.Orientation.Vertical;

            StackPanel button_panel = new StackPanel();

            Image img = new Image();
            img.Tap += DimmerControl_Tap;
            img.Margin = new Thickness(360, 0, 0, 0);
            Uri uri = new Uri("Assets/Images/exit_button.png", UriKind.Relative);
            BitmapImage imgsource = new BitmapImage(uri);
            img.Height = 60;
            img.Source = imgsource;

            button_panel.Children.Add(img);
            outer_panel.Children.Add(button_panel);

            return outer_panel;
        }

        /// <summary>
        /// Set content of popup
        /// </summary>
        /// <returns></returns>
        private StackPanel Via_Internet_Popup_Content()
        {
            StackPanel temp = new StackPanel();

            TextBlock text = new TextBlock();
            text.Text = "Plannen via internet";
            text.FontWeight = FontWeights.Bold;
            text.FontSize = 35;
            text.Foreground = new SolidColorBrush(Colors.Black);
            text.Margin = new Thickness(20, 0, 0, 20);

            TextBlock text1 = new TextBlock();
            text1.Text = "Op de website van Qbuzz (www.qbuzz.nl) vindt u een compleet overzicht van alle haltes en buslijnen in uw buurt als u uw adres invult. Ook vindt u hier een overzicht van alle vertrektijden. Is het lijnnummer al bekend, dan kunt u door direct het lijnnummer in te vullen de dienstregeling opvragen.";
            text1.TextWrapping = TextWrapping.Wrap;
            text1.Margin = new Thickness(20, 0, 20, 20);
            text1.Foreground = new SolidColorBrush(Colors.Black);
            text1.FontSize = 18;

            TextBlock text2 = new TextBlock();
            text2.Text = "Via de website www.9292.nl kunt u een compleet reisadvies opvragen, inclusief bijbehorende kosten. U vult in waarvandaan u vertrekt en waar u heen wilt. U krijgt dan een lijst met alle mogelijkheden. In de lijst kunt u een reis aanklikken en zien welke buslijn(en) u moet hebben. U kunt ook alvast bekijken wat de reis kost.";
            text2.TextWrapping = TextWrapping.Wrap;
            text2.Margin = new Thickness(20, 0, 20, 10);
            text2.Foreground = new SolidColorBrush(Colors.Black);
            text2.FontSize = 18;

            temp.Children.Add(text);
            temp.Children.Add(text1);
            temp.Children.Add(text2);

            return temp;
        }

        /// <summary>
        /// Set content of popup
        /// </summary>
        /// <returns></returns>
        private StackPanel Telephone_Popup_Content()
        {
            StackPanel temp = new StackPanel();

            TextBlock text = new TextBlock();
            text.Text = "Bustijden telefonisch opvragen";
            text.FontWeight = FontWeights.Bold;
            text.TextWrapping = TextWrapping.Wrap;
            text.FontSize = 35;
            text.Foreground = new SolidColorBrush(Colors.Black);
            text.Margin = new Thickness(20, 0, 0, 20);

            TextBlock text1 = new TextBlock();
            text1.Text = "Qbuzz Groningen Drenthe";
            text1.FontWeight = FontWeights.Bold;
            text1.FontSize = 25;
            text1.Foreground = new SolidColorBrush(Colors.Black);
            text1.Margin = new Thickness(15, 0, 0, 15);

            TextBlock text2 = new TextBlock();
            text2.Text = "U kunt bij de klantenservice van Qbuzz de dienstregeling van één of meer buslijnen in Groningen en Drenthe opvragen. U krijgt gratis een print thuisgestuurd U kunt dan thuis bekijken wanneer de bussen rijden. Het telefoonnummer van de klantenservice is 0900 - 72 89 965 (lokaal tarief).";
            text2.TextWrapping = TextWrapping.Wrap;
            text2.Margin = new Thickness(20, 0, 20, 40);
            text2.Foreground = new SolidColorBrush(Colors.Black);
            text2.FontSize = 18;
            
            TextBlock text3 = new TextBlock();
            text3.Text = "Klantenservice 9292";
            text3.FontWeight = FontWeights.Bold;
            text3.FontSize = 25;
            text3.Foreground = new SolidColorBrush(Colors.Black);
            text3.Margin = new Thickness(15, 0, 0, 15);

            TextBlock text4 = new TextBlock();
            text4.Text = "Wilt u hulp bij het plannen van uw reis, dan kunt u bellen met de klantenservice van 9292. Het telefoonnummer is 0900 - 9292 (70 eurocent per minuut). 9292 geeft informatie over reizen in heel Nederland";
            text4.TextWrapping = TextWrapping.Wrap;
            text4.Margin = new Thickness(20, 0, 20, 10);
            text4.Foreground = new SolidColorBrush(Colors.Black);
            text4.FontSize = 18;

            temp.Children.Add(text);
            temp.Children.Add(text1);
            temp.Children.Add(text2);
            temp.Children.Add(text3);
            temp.Children.Add(text4);

            return temp;
        }

        /// <summary>
        /// Set content of popup
        /// </summary>
        /// <returns></returns>
        private StackPanel Busstop_Popup_Content()
        {
            StackPanel temp = new StackPanel();

            TextBlock text = new TextBlock();
            text.Text = "Bustijden bekijken bij de bushalte";
            text.FontWeight = FontWeights.Bold;
            text.TextWrapping = TextWrapping.Wrap;
            text.FontSize = 35;
            text.Foreground = new SolidColorBrush(Colors.Black);
            text.Margin = new Thickness(20, 0, 0, 20);

            TextBlock text1 = new TextBlock();
            text1.Text = "Bij iedere halte vindt u een dienstregeling met de vertrektijden van de bussen, die daar vandaan vertrekken.";
            text1.TextWrapping = TextWrapping.Wrap;
            text1.Margin = new Thickness(20, 0, 20, 40);
            text1.Foreground = new SolidColorBrush(Colors.Black);
            text1.FontSize = 18;

            Image img = new Image();
            Uri uri = new Uri("Assets/Images/qbuzz.png", UriKind.Relative);
            BitmapImage imgsource = new BitmapImage(uri);
            img.Width = 400;
            img.Source = imgsource;

            temp.Children.Add(text);
            temp.Children.Add(text1);
            temp.Children.Add(img);

            return temp;
        }
        
    }
}