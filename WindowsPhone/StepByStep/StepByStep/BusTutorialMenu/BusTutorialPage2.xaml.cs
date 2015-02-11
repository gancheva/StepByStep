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
using System.Windows.Media.Imaging;
using System.Windows.Controls.Primitives;
using System.Windows.Documents;

namespace Step_By_Step
{
    public partial class BusTutorialPage2 : PhoneApplicationPage
    {
        Popup popup;
        public BusTutorialPage2()
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
        /// Go back
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void back_tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            NavigationService.Navigate(new Uri("/BusTutorialMenu/BusTutorialPage1.xaml", UriKind.Relative));
        }

        /// <summary>
        /// Go to next view
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void next_tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            NavigationService.Navigate(new Uri("/BusTutorialMenu/BusTutorialPage3.xaml", UriKind.Relative));
        }

        /// <summary>
        /// Open popup
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void eurokaartjes_tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
           DimmerControl.Visibility = System.Windows.Visibility.Visible;
           Display_Euroticket_Popup();
        }

        /// <summary>
        /// Open popup
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void ov_chipkaart_tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            DimmerControl.Visibility = System.Windows.Visibility.Visible;
            Display_OVchipcard_Popup();
        }

        /// <summary>
        /// Open video view
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void open_video_tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            NavigationService.Navigate(new Uri("/BusTutorialMenu/BusTutorialVideo1.xaml", UriKind.Relative));
        }

        /// <summary>
        /// Override OnBackKeyPress
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
        /// Create popup
        /// </summary>
        /// <returns></returns>
        private StackPanel Outter_Popup_Panel()
        {
            StackPanel outter_panel = new StackPanel();
            outter_panel.Height = 700;
            outter_panel.Width = 440;
            outter_panel.Background = new SolidColorBrush(Colors.White);
            outter_panel.Orientation = System.Windows.Controls.Orientation.Vertical;

            StackPanel button_panel = new StackPanel();

            Image img = new Image();
            img.Tap += DimmerControl_Tap;
            img.Margin = new Thickness(360, 0, 0, 0);
            Uri uri = new Uri("Assets/Images/exit_button.png", UriKind.Relative);
            BitmapImage imgsource = new BitmapImage(uri);
            img.Height = 60;
            img.Source = imgsource;

            button_panel.Children.Add(img);
            outter_panel.Children.Add(button_panel);

            return outter_panel;
        }

        /// <summary>
        /// Display popup
        /// </summary>
        private void Display_Euroticket_Popup()
        {
            StackPanel outter_panel = Outter_Popup_Panel();
            outter_panel.Children.Add(Euroticket_Popup_Content());
            popup.Child = outter_panel;

            popup.VerticalOffset = 20;
            popup.HorizontalOffset = 20;

            popup.IsOpen = true;
        }

        /// <summary>
        /// Display popup
        /// </summary>
        private void Display_OVchipcard_Popup()
        {
            StackPanel outter_panel = Outter_Popup_Panel();
            outter_panel.Children.Add(OVchipcard_Popup_Content());
            popup.Child = outter_panel;

            popup.VerticalOffset = 20;
            popup.HorizontalOffset = 20;

            popup.IsOpen = true;
        }

        /// <summary>
        /// Set content of popup
        /// </summary>
        /// <returns></returns>
        private StackPanel Euroticket_Popup_Content()
        {
            StackPanel temp = new StackPanel();

            TextBlock text = new TextBlock();
            text.Text = "Eurokaartjes";
            text.FontWeight = FontWeights.Bold;
            text.FontSize = 35;
            text.Foreground = new SolidColorBrush(Colors.Black);
            text.Margin = new Thickness(20, 0, 0, 20);

            TextBlock text1 = new TextBlock();
            text1.Text = "Reist u maar af en toe met de bus? Dan kunt u in Groningen en Drenthe tegen contante betaling een Eurokaartje bij de chauffeur kopen. Deze kaartjes zijn verkrijgbaar in vier soorten: 2-Euro kaartjes, 5-Euro kaartjes, 8-Euro kaartjes en Dagkaarten. De afstand die u aflegt, bepaald de variant van het kaartje.";
            text1.TextWrapping = TextWrapping.Wrap;
            text1.Margin = new Thickness(20, 0, 20, 20);
            text1.Foreground = new SolidColorBrush(Colors.Black);
            text1.FontSize = 18;

            Image img = new Image();
            Uri uri = new Uri("Assets/Images/table for popup.png", UriKind.Relative);
            BitmapImage imgsource = new BitmapImage(uri);
            img.Width = 400;
            img.Source = imgsource;

            temp.Children.Add(text);
            temp.Children.Add(text1);
            temp.Children.Add(img);

            return temp;
        }

        /// <summary>
        /// Set content of popup
        /// </summary>
        /// <returns></returns>
        private StackPanel OVchipcard_Popup_Content()
        {
            StackPanel temp = new StackPanel();

            TextBlock text = new TextBlock();
            text.Text = "De OV-chipKaart";
            text.FontWeight = FontWeights.Bold;
            text.FontSize = 35;
            text.Foreground = new SolidColorBrush(Colors.Black);
            text.Margin = new Thickness(20, 0, 0, 20);
                       
            TextBlock text1 = new TextBlock();
            text1.Text = "Belangrijk: Een uitgebreide uitleg over de OV-chipkaart is te vinden in het hoofdmenu.";
            text1.Foreground = new SolidColorBrush(Colors.Black);
            text1.FontSize = 18;
            text1.TextWrapping = TextWrapping.Wrap;
            text1.Margin = new Thickness(20, 0, 20, 20);

            TextBlock text2 = new TextBlock();
            text2.Text = "De OV-chipkaart is het nieuwe vervoersbewijs in Nederland. Je kunt hem gebruiken om te reizen met de trein, de bus, de tram en de metro. De OV-chipkaart komt in de plaats van strippenkaarten en treinkaartjes.";
            text2.Foreground = new SolidColorBrush(Colors.Black);
            text2.FontSize = 18;
            text2.TextWrapping = TextWrapping.Wrap;
            text2.Margin = new Thickness(20, 0, 20, 20);
            
            TextBlock text3 = new TextBlock();
            text3.Text = "Een OV-chipkaart is te koop bij een Qbuzz servicepunt, op het station of bij een OV-chipkaart automaat. Een persoonlijke OV-chipkaart is aan te vragen via internet of via een formulier.";
            text3.Foreground = new SolidColorBrush(Colors.Black);
            text3.FontSize = 18;
            text3.TextWrapping = TextWrapping.Wrap;
            text3.Margin = new Thickness(20, 0, 20, 20);

            TextBlock text4 = new TextBlock();
            text4.Text = "Als u een OV-chipkaart hebt, kunt u deze opladen bij een OV-chipkaart automaat. Deze zijn vaak te vinden in winkels als Primera of in supermarkten, en op het station.";
            text4.Foreground = new SolidColorBrush(Colors.Black);
            text4.FontSize = 18;
            text4.TextWrapping = TextWrapping.Wrap;
            text4.Margin = new Thickness(20, 0, 20, 20);

            TextBlock text5 = new TextBlock();
            text5.Text = "Voor u de trein in stapt of als u de bus in stapt, houdt u de OV-chipkaart simpelweg voor de lezer, en als u weer uitstapt, doet u dit weer. Het systeem berekent dan welke reis u heeft gemaakt, en zal de kosten daarvoor automatisch van uw saldo afschrijven.";
            text5.Foreground = new SolidColorBrush(Colors.Black);
            text5.FontSize = 18;
            text5.TextWrapping = TextWrapping.Wrap;
            text5.Margin = new Thickness(20, 0, 20, 20);
                                  
            temp.Children.Add(text);
            temp.Children.Add(text1);
            temp.Children.Add(text2);
            temp.Children.Add(text3);
            temp.Children.Add(text4);
            temp.Children.Add(text5);
            
            return temp;
        }
    }
}