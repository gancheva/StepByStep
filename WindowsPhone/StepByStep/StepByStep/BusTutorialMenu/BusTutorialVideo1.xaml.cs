using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;

namespace Step_By_Step.BusTutorialMenu
{
    public partial class BusTutorialVideo1 : PhoneApplicationPage
    {
        public BusTutorialVideo1()
        {
            InitializeComponent();
        }

        /// <summary>
        /// Initialize media element and start video
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void myvideo_Loaded(object sender, RoutedEventArgs e)
        {
            MediaElement myvideo = sender as MediaElement;
            myvideo.Width = this.Width; //Windows.Current.Bounds.Width;
            myvideo.Height = this.Height; //Window.Current.Bounds.Height;
            myvideo.MediaEnded += new RoutedEventHandler(myMediaEnded);
            //http ://ecn.channel9.msdn.com/o9/content/smf/progressivecontent/wildlife.wmv extra video for test
            myvideo.Source = new Uri("http://itract.cs.kau.se:8090/wp-content/uploads/2013/10/ov.mp4", UriKind.Absolute);
            if (myvideo != null)
            {
                myvideo.Play();
            }   
        }

        /// <summary>
        /// Stop media
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void myMediaEnded(object sender, RoutedEventArgs e)
        {
            MediaElement myvideo = sender as MediaElement;
            myvideo.Stop();
            NavigationService.Navigate(new Uri("/BusTutorialMenu/BusTutorialPage2.xaml", UriKind.Relative));
        }
    }
}