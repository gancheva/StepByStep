using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.IO;
using System.Threading;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;
using System.Device.Location;
using System.Windows.Media;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Json;
using System.Net.Browser;
using System.Windows.Shapes;
using System.Windows.Input;

namespace Step_By_Step.MainMenu
{
    public partial class BushaltesInfo : PhoneApplicationPage
    {
        GeoCoordinateWatcher myLocationWatcher = null;
        string latitude = "";
        string longitude = "";
        Boolean map_button_free = false;
        public static BusStop[] pins { get; set; }
        public static BusStop selectedBS { get; set; }

        public BushaltesInfo()
        {
            InitializeComponent();
            GetGeoLocation();
        }

        /// <summary>
        /// Initialize the GeoCoordinateWatcher and set some handlers
        /// </summary>
        private void GetGeoLocation() 
        {
            // Reinitialize the GeoCoordinateWatcher
            myLocationWatcher = new GeoCoordinateWatcher(GeoPositionAccuracy.High);
            myLocationWatcher.MovementThreshold = 20; //20 m

            // Add event handlers for StatusChanged and PositionChanged events
            myLocationWatcher.StatusChanged += new EventHandler<GeoPositionStatusChangedEventArgs>(watcher_StatusChanged);
            myLocationWatcher.PositionChanged += new EventHandler<GeoPositionChangedEventArgs<GeoCoordinate>>(watcher_PositionChanged);

            // Start data acquisition
            myLocationWatcher.Start();
        }

        /// <summary>
        /// Handler for the StatusChanged event. This invokes MyStatusChanged on the UI thread and
        /// passes the GeoPositionStatusChangedEventArgs
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        void watcher_StatusChanged(object sender, GeoPositionStatusChangedEventArgs e)
        {
            //the dispatcher dispatches to the specified method when a status change occurs
            Deployment.Current.Dispatcher.BeginInvoke(() => MyStatusChanged(e));

        }

        /// <summary>
        /// Custom method called from the StatusChanged event handler
        /// </summary>
        /// <param name="e"></param>
        void MyStatusChanged(GeoPositionStatusChangedEventArgs e)
        {
            switch (e.Status)
            {
                case GeoPositionStatus.Disabled:
                    if (myLocationWatcher.Permission == GeoPositionPermission.Denied)
                    {
                        MessageBox.Show("You have disabled the location Service on your device.\n");
                    }
                    else
                    {
                        MessageBox.Show("Location is not functioning on this device\n");
                    }
                    NavigationService.Navigate(new Uri("/MainPage.xaml", UriKind.Relative));
                    break;
                case GeoPositionStatus.Initializing:
                    // if needed make notification as the following
                    //MessageBox.Show("Location service is initializing...\n");
                    break;
                case GeoPositionStatus.NoData:
                    MessageBox.Show("Location data is not available.\n");
                    NavigationService.Navigate(new Uri("/MainPage.xaml", UriKind.Relative));
                    break;
                case GeoPositionStatus.Ready:
                    // if needed make notification as the following 
                    //MessageBox.Show("Location data is available.\n");
                    break;
            }
        }

        /// <summary>
        /// Handler for the PositionChanged event. This invokes MyStatusChanged on the UI thread and
        /// passes the GeoPositionStatusChangedEventArgs
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        void watcher_PositionChanged(object sender, GeoPositionChangedEventArgs<GeoCoordinate> e)
        {
            //Dispatcher invokes the event for position change.
            Deployment.Current.Dispatcher.BeginInvoke(() => MyPositionChanged(e));
        }

        /// <summary>
        /// Custom method called from the PositionChanged event handler
        /// </summary>
        /// <param name="e"></param>
        void MyPositionChanged(GeoPositionChangedEventArgs<GeoCoordinate> e)
        {
            // Update the TextBlocks to show the current location
            this.latitude = e.Position.Location.Latitude.ToString("0.000000000000000");
            this.longitude = e.Position.Location.Longitude.ToString("0.000000000000000");
            
            // Stopping the watcher 
            myLocationWatcher.Stop();
            this.map_button_free = false;

            GetBusStops();
        }

        /// <summary>
        /// Method to send request to the Open Trip Planner API for getting the nearest bus stops
        /// </summary>
        private void GetBusStops() 
        {
            // Latitude is a decimal number between -90.0 and 90.0.
            // Longitude is a decimal number between -180.0 and 180.0.
            double range = 0.5;
            double inLat = Convert.ToDouble(this.latitude);
            double inLong = Convert.ToDouble(this.longitude);

            double latrange = range / 69.172;
            double longrange = Math.Abs(range / (Math.Cos(inLat) * 69.172));
            double minlat = inLat - latrange;
            double maxlat = inLat + latrange;
            double minlon = inLong - longrange;
            double maxlon = inLong + longrange;
            
            string stringUri = "http://itract.cs.kau.se:8081/proxy/api/transit/stopsInArea?lat=" + this.latitude + "&lon=" + this.longitude + "&southWestLat=" + minlat + "&southWestLon=" + minlon + "&northEastLat=" + maxlat + "&northEastLon=" + maxlon;
            System.Uri uri = new Uri(stringUri);
            // Create a HttpWebrequest object to the desired URL.
            HttpWebRequest request = (HttpWebRequest)HttpWebRequest.Create(uri);
            request.BeginGetResponse(new AsyncCallback(ReadWebRequestCallback), request);

        }

        /// <summary>
        /// Async method to read the API's response 
        /// </summary>
        /// <param name="callbackResult"></param>
        private void ReadWebRequestCallback(IAsyncResult callbackResult)
        {
            try 
            {    
                HttpWebRequest myRequest = default(HttpWebRequest);
                HttpWebResponse myResponse = default(HttpWebResponse);
                myRequest = (HttpWebRequest) callbackResult.AsyncState;
                myResponse = (HttpWebResponse) myRequest.EndGetResponse(callbackResult);

                using (StreamReader httpwebStreamReader = new StreamReader(myResponse.GetResponseStream()))
                {
                    string result = httpwebStreamReader.ReadToEnd();
                    result = result.Substring(1, result.Length - 2);

                    BusStops busStopsObj = null;
                    
                    using (MemoryStream stream = new MemoryStream(Encoding.Unicode.GetBytes(result)))
                    {
                        DataContractJsonSerializer serializer = new DataContractJsonSerializer(typeof(BusStops));
                        busStopsObj = serializer.ReadObject(stream) as BusStops;
                    }

                    if (busStopsObj.busStops != null && busStopsObj.busStops.Length > 0)
                    {
                        foreach (BusStop current in busStopsObj.busStops)
                        {
                            current.dist = getDistance(current.lat, current.lon, Convert.ToDouble(this.latitude), Convert.ToDouble(this.longitude));
                        }

                        int counter = 0;
                        foreach (BusStop current in busStopsObj.busStops)
                        {
                            Deployment.Current.Dispatcher.BeginInvoke(() =>
                            {
                                counter++;
                                StackPanel wrapper = new StackPanel() { Orientation = System.Windows.Controls.Orientation.Vertical }; 
                                TextBlock text = new TextBlock() { TextWrapping = TextWrapping.Wrap, Visibility = System.Windows.Visibility.Visible, Foreground = new SolidColorBrush(Colors.Black), VerticalAlignment = System.Windows.VerticalAlignment.Center, FontSize = 24, Margin = new System.Windows.Thickness(5, 15, 5, 15), Text = " " + counter + "   " + current.name + "   " + current.dist + "km" };
                                Line line = new Line() { X1 = 0, X2 = 1000, Y1 = 0, Y2 = 0, StrokeThickness = 2, Stroke = new SolidColorBrush(Colors.Gray) };
                                wrapper.Children.Add(text);
                                wrapper.Children.Add(line);
                                wrapper.Tap += busStopSelected;
                                busStopsList.Children.Add(wrapper);
                            });
                        }
                        pins = busStopsObj.busStops;
                    }
                    this.map_button_free = true;
                }

                myResponse.Close();
            } catch (Exception e) 
            { 
                Console.WriteLine("StepByStep - An error occurred: '{0}'", e); 
            }
        }

        /// <summary>
        /// When a bus stop is selected this method will be executed
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void busStopSelected (object sender, EventArgs e) {

            StackPanel panel = sender as StackPanel;
            TextBlock selected = panel.Children.OfType<TextBlock>().FirstOrDefault();
            string number = selected.Text.Substring(0, 4);
            number = number.Trim();
            int i = Convert.ToInt32(number);
            selectedBS = pins.ElementAt(i - 1);
            NavigationService.Navigate(new Uri("/BusStopsInfo/BusStopTimeTable.xaml", UriKind.Relative));

        }

        /// <summary>
        /// Method for calculating the distance between two point om a map
        /// </summary>
        /// <param name="lat1"></param>
        /// <param name="lat2"></param>
        /// <param name="lon1"></param>
        /// <param name="lon2"></param>
        private double getDistance(double lat1, double lon1, double lat2, double lon2) 
        {
            //http://en.wikipedia.org/wiki/Haversine_formula
            
            double R = 6371; // the earth's radius in km
            double dLat = degToRad(lat2 - lat1);
            double dLon = degToRad(lon2 - lon1);
            double a = Math.Sin(dLat / 2) * Math.Sin(dLat / 2) + Math.Cos(degToRad(lat1)) * Math.Cos(degToRad(lat2)) * Math.Sin(dLon / 2) * Math.Sin(dLon / 2);
            double c = 2 * Math.Atan2(Math.Sqrt(a), Math.Sqrt(1 - a));
            double dist = R * c;
            string s = string.Format("{0:0.00}", dist);
            dist = Convert.ToDouble(s);
            return dist;
        }

        /// <summary>
        /// Help method for coverting degrees to radius
        /// </summary>
        /// <param name="deg"></param>
        private double degToRad(double deg) {
            return (deg * Math.PI / 180.0);
        }

        /// <summary>
        /// Help method for converting radius to degrees
        /// </summary>
        /// <param name="rad"></param>
        private double radToDeg(double rad) {
            return (rad / Math.PI * 180.0);
        }

        /// <summary>
        /// Back to home menu
        /// </summary>
        /// <param name="e"></param>
        /// <param name="sender"></param>
        private void Homebutton_tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            NavigationService.Navigate(new Uri("/MainPage.xaml", UriKind.Relative));
        }

        /// <summary>
        /// Go to map
        /// </summary>
        /// <param name="e"></param>
        /// <param name="sender"></param>
        private void map_tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            if (this.map_button_free & latitude != "" & longitude != "") NavigationService.Navigate(new Uri("/BusStopsInfo/Map.xaml?latitude=" + latitude + "&longitude=" + longitude, UriKind.Relative)); 
            
        }

        /// <summary>
        /// Overrides OnBackKeyPress 
        /// </summary>
        /// <param name="e"></param>
        protected override void OnBackKeyPress(System.ComponentModel.CancelEventArgs e)
        {
            e.Cancel = true;
            NavigationService.Navigate(new Uri("/MainPage.xaml", UriKind.Relative));
        }

    }

        [DataContract]
        public class BusStop
        {
            [DataMember]
            public string id { get; set; }
            [DataMember]
            public string name { get; set; }
            [DataMember]
            public string agencyId { get; set; }
            [DataMember]
            public double lat { get; set; }
            [DataMember]
            public double lon { get; set; }

            public double dist { get; set; }
        }

        [DataContract]
        public class BusStops
        {
            [DataMember]
            public string graphId { get; set; }
            [DataMember (Name = "data")]
            public BusStop[] busStops { get; set; }
        }


}