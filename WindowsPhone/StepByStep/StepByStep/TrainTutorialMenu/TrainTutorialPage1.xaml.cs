using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;
using System.Windows.Media;
using System.Windows.Controls.Primitives;
using System.Windows.Media.Imaging;

namespace Step_By_Step.TrainTutorialMenu
{
    public partial class TrainTutorialPage1 : PhoneApplicationPage
    {
        Popup popup;
        public TrainTutorialPage1()
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
            NavigationService.Navigate(new Uri("/MainMenu/TrainTutorial.xaml", UriKind.Relative));
        }

        /// <summary>
        /// Go to next view
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void next_tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            NavigationService.Navigate(new Uri("/TrainTutorialMenu/TrainTutorialPage2.xaml", UriKind.Relative));
        }

        /// <summary>
        /// Dimmer control and display popup 
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void algemeen_tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            DimmerControl.Visibility = System.Windows.Visibility.Visible;
            Display_Algemeen_Popup();
        }

        /// <summary>
        /// Dimmer control and display popup 
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void ns_tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            DimmerControl.Visibility = System.Windows.Visibility.Visible;
            Display_NS_Popup();
        }

        /// <summary>
        /// Dimmer control and display popup 
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void arriva_tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            DimmerControl.Visibility = System.Windows.Visibility.Visible;
            Display_Arriva_Popup();
        }

        /// <summary>
        /// Override OnBackKeyPress
        /// </summary>
        /// <param name="e"></param>
        protected override void OnBackKeyPress(System.ComponentModel.CancelEventArgs e)
        {
            e.Cancel = true;
            popup.IsOpen = false;
            NavigationService.Navigate(new Uri("/MainMenu/TrainTutorial.xaml", UriKind.Relative));
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
        private void Display_Algemeen_Popup()
        {
            StackPanel outer_panel = Outer_Popup_Panel();
            outer_panel.Children.Add(Algemeen_Popup_Content());
            popup.Child = outer_panel;

            popup.VerticalOffset = 20;
            popup.HorizontalOffset = 20;

            popup.IsOpen = true;
        }

        /// <summary>
        /// Display popup
        /// </summary>
        private void Display_NS_Popup()
        {
            StackPanel outer_panel = Outer_Popup_Panel();
            outer_panel.Children.Add(NS_Popup_Content());
            popup.Child = outer_panel;

            popup.VerticalOffset = 20;
            popup.HorizontalOffset = 20;

            popup.IsOpen = true;
        }

        /// <summary>
        /// Display popup
        /// </summary>
        private void Display_Arriva_Popup()
        {
            StackPanel outer_panel = Outer_Popup_Panel();
            outer_panel.Children.Add(Arriva_Popup_Content());
            popup.Child = outer_panel;

            popup.VerticalOffset = 20;
            popup.HorizontalOffset = 20;

            popup.IsOpen = true;
        }
        
        /// <summary>
        /// Set popup content
        /// </summary>
        /// <returns></returns>
        private UIElement Algemeen_Popup_Content()
        {
            StackPanel temp = new StackPanel();

            TextBlock text = new TextBlock();
            text.Text = "Plannen via www.9292.nl";
            text.FontWeight = FontWeights.Bold;
            text.FontSize = 35;
            text.Foreground = new SolidColorBrush(Colors.Black);
            text.Margin = new Thickness(20, 0, 0, 20);

            TextBlock text1 = new TextBlock();
            text1.Text = "Op www.9292.nl kunt u uw reis plannen door uw startlocatie en eindbestemming op te geven, en eventueel nog een datum en tijd waarop u wil reizen.";
            text1.TextWrapping = TextWrapping.Wrap;
            text1.Margin = new Thickness(20, 0, 20, 40);
            text1.Foreground = new SolidColorBrush(Colors.Black);
            text1.FontSize = 18;

            TextBlock text2 = new TextBlock();
            text2.Text = "Vertrek/aankomst";
            text2.FontWeight = FontWeights.Bold;
            text2.FontSize = 25;
            text2.Foreground = new SolidColorBrush(Colors.Black);
            text2.Margin = new Thickness(15, 0, 0, 15);

            TextBlock text3 = new TextBlock();
            text3.Text = "Als u de optie \"Vertrek\" aanvinkt, wordt ervan uitgegaan dat u om de opgegeven tijd wil vertrekken; als u de optie \"Aankomst\" aanvinkt dat u om de opgegeven tijd aan wil komen.";
            text3.TextWrapping = TextWrapping.Wrap;
            text3.Margin = new Thickness(20, 0, 20, 40);
            text3.Foreground = new SolidColorBrush(Colors.Black);
            text3.FontSize = 18;

            TextBlock text4 = new TextBlock();
            text4.Text = "Welke vervoerder?";
            text4.FontWeight = FontWeights.Bold;
            text4.FontSize = 25;
            text4.Foreground = new SolidColorBrush(Colors.Black);
            text4.Margin = new Thickness(15, 0, 0, 15);

            TextBlock text5 = new TextBlock();
            text5.Text = "Nadat u op \"Plan mijn reis\" heeft geklikt, verschijnt uw reisadvies in beeld. Als u met de trein reist, ziet u in de regel van de kop die het type trein aangeeft, bijvoorbeeld \"Sprinter\" of \"Intercity\", aan de rechterkant met welke vervoerder u reist. Dat kan bijvoorbeeld NS zijn of Arriva.";
            text5.TextWrapping = TextWrapping.Wrap;
            text5.Margin = new Thickness(20, 0, 20, 40);
            text5.Foreground = new SolidColorBrush(Colors.Black);
            text5.FontSize = 18;

            temp.Children.Add(text);
            temp.Children.Add(text1);
            temp.Children.Add(text2);
            temp.Children.Add(text3);
            temp.Children.Add(text4);
            temp.Children.Add(text5);

            return temp;
        }

        /// <summary>
        /// Set popup content
        /// </summary>
        /// <returns></returns>
        private UIElement NS_Popup_Content()
        {
            StackPanel temp = new StackPanel();

            TextBlock text = new TextBlock();
            text.Text = "Plannen via www.ns.nl";
            text.FontWeight = FontWeights.Bold;
            text.FontSize = 35;
            text.Foreground = new SolidColorBrush(Colors.Black);
            text.Margin = new Thickness(20, 0, 0, 20);

            TextBlock text1 = new TextBlock();
            text1.Text = "Op www.ns.nl kunt u uw reis plannen door uw startlocatie en eindbestemming, en eventueel nog een datum en tijd waarop u wilt reizen op te geven.";
            text1.TextWrapping = TextWrapping.Wrap;
            text1.Margin = new Thickness(20, 0, 20, 40);
            text1.Foreground = new SolidColorBrush(Colors.Black);
            text1.FontSize = 18;

            TextBlock text2 = new TextBlock();
            text2.Text = "Vertrek/aankomst";
            text2.FontWeight = FontWeights.Bold;
            text2.FontSize = 25;
            text2.Foreground = new SolidColorBrush(Colors.Black);
            text2.Margin = new Thickness(15, 0, 0, 15);

            TextBlock text3 = new TextBlock();
            text3.Text = "Als u de optie \"Vertrek\" aanvinkt, wordt ervan uitgegaan dat u om de opgegeven tijd wil vertrekken; als u de optie \"Aankomst\" aanvinkt dat u om de opgegeven tijd aan wil komen.";
            text3.TextWrapping = TextWrapping.Wrap;
            text3.Margin = new Thickness(20, 0, 20, 40);
            text3.Foreground = new SolidColorBrush(Colors.Black);
            text3.FontSize = 18;

            temp.Children.Add(text);
            temp.Children.Add(text1);
            temp.Children.Add(text2);
            temp.Children.Add(text3);

            return temp;
        }

        /// <summary>
        /// Set popup content
        /// </summary>
        /// <returns></returns>
        private UIElement Arriva_Popup_Content()
        {
            StackPanel temp = new StackPanel();

            TextBlock text = new TextBlock();
            text.Text = "Plannen via www.arriva.nl";
            text.FontWeight = FontWeights.Bold;
            text.TextWrapping = TextWrapping.Wrap;
            text.FontSize = 35;
            text.Foreground = new SolidColorBrush(Colors.Black);
            text.Margin = new Thickness(20, 0, 0, 20);

            TextBlock text1 = new TextBlock();
            text1.Text = "Op www.arriva.nl kunt u uw reis plannen door uw startlocatie en eindbestemming, en eventueel nog een datum en tijd waarop u wilt reizen op te geven.";
            text1.TextWrapping = TextWrapping.Wrap;
            text1.Margin = new Thickness(20, 0, 20, 40);
            text1.Foreground = new SolidColorBrush(Colors.Black);
            text1.FontSize = 18;

            TextBlock text2 = new TextBlock();
            text2.Text = "Vertrek/aankomst";
            text2.FontWeight = FontWeights.Bold;
            text2.FontSize = 25;
            text2.Foreground = new SolidColorBrush(Colors.Black);
            text2.Margin = new Thickness(15, 0, 0, 15);

            TextBlock text3 = new TextBlock();
            text3.Text = "Als u de optie \"Vertrek\" aanvinkt, wordt ervan uitgegaan dat u om de opgegeven tijd wil vertrekken; als u de optie \"Aankomst\" aanvinkt dat u om de opgegeven tijd aan wil komen.";
            text3.TextWrapping = TextWrapping.Wrap;
            text3.Margin = new Thickness(20, 0, 20, 40);
            text3.Foreground = new SolidColorBrush(Colors.Black);
            text3.FontSize = 18;

            temp.Children.Add(text);
            temp.Children.Add(text1);
            temp.Children.Add(text2);
            temp.Children.Add(text3);

            return temp;
        }        

        /// <summary>
        /// Create popup
        /// </summary>
        /// <returns></returns>
        private StackPanel Outer_Popup_Panel()
        {
            StackPanel outer_panel = new StackPanel();
            outer_panel.Height = 700;
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
    }
}