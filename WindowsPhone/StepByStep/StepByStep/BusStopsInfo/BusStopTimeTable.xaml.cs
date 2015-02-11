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
using System.Windows.Media;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Json;
using System.Net.Browser;
using System.Windows.Shapes;

namespace Step_By_Step.BusStopsInfo
{
    public partial class BusStopTimeTable : PhoneApplicationPage
    {

        Step_By_Step.MainMenu.BusStop busStop { get; set; }

        public BusStopTimeTable()
        {
            InitializeComponent();
        }

        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            busStop = Step_By_Step.MainMenu.BushaltesInfo.selectedBS;
            showTitle(busStop.name, 26) ;
            //
            //NB: The uri here does not use data from the Netherlands since it was not available!  
            //
            string stringUri = "http://itract.cs.kau.se:8081/proxy/api/transit/arrivalsAndDeparturesForStop?lat=59.37919176435972&lon=13.498671776845185&agencyId=601&stopId=7421275&startTime=1382602879730&endTime=1382689279730";
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
                    if (result.Contains("}]}},{")) result = result.Substring(1, result.IndexOf("}]}}")) + "]}}";

                    // test json parser
                    //result = "{\"graphId\":\"Test_otp\",\"data\":{\"departures\":[{\"routeId\":\"601_1_BLT_265\",\"routeShortName\":\"1\",\"tripHeadsign\":\"Karlstad Campus Futurum\",\"time\":\"1382603100000\",\"delay\":\"0\",\"agencyName\":\"Karlstadsbuss\",\"routeType\":3}],\"arrivals\":[{\"routeId\":\"601_1_BLT_265\",\"routeShortName\":\"1\",\"tripHeadsign\":\"Karlstad Campus Futurum\",\"time\":\"1382603100000\",\"delay\":\"0\",\"agencyName\":\"Karlstadsbuss\",\"routeType\":3}]}}";

                    TimeTable timeTable = null;
                    using (MemoryStream stream = new MemoryStream(Encoding.Unicode.GetBytes(result)))
                    {
                        DataContractJsonSerializer serializer = new DataContractJsonSerializer(typeof(TimeTable));
                        timeTable = serializer.ReadObject(stream) as TimeTable;
                    }

                    if (timeTable != null) 
                    {
                        DataList dataList = timeTable.data;
                        
                        if (dataList.arrivals != null && dataList.arrivals.Length > 0)
                        {
                            showTitle("Arrivals", 24);
                            foreach (Info current in dataList.arrivals) showInfo(current); 
                        }
                        
                        if (dataList.departures != null && dataList.departures.Length > 0)
                        {
                            showTitle("Departures", 24);
                            foreach (Info current in dataList.arrivals) showInfo(current);   
                        }
                    }  
                }

                myResponse.Close();
            } catch (Exception e) 
            { 
                Console.WriteLine("StepByStep - An error occurred: '{0}'", e); 
            }
        }

        private void showTitle(string title, int size) 
        {
            Deployment.Current.Dispatcher.BeginInvoke(() =>
            {
                Line line = new Line() { X1 = 0, X2 = 1000, Y1 = 0, Y2 = 0, StrokeThickness = 2, Stroke = new SolidColorBrush(Colors.Gray) };
                TextBlock text = new TextBlock() { TextWrapping = TextWrapping.Wrap, Visibility = System.Windows.Visibility.Visible, Foreground = new SolidColorBrush(Colors.Black), VerticalAlignment = System.Windows.VerticalAlignment.Center, HorizontalAlignment = System.Windows.HorizontalAlignment.Center, FontSize = size, Text = title, FontWeight = FontWeights.Bold };
                busStopTimeTable.Children.Add(text);
                busStopTimeTable.Children.Add(line);
            });
        }

        private void showInfo(Info current)
        {
            Deployment.Current.Dispatcher.BeginInvoke(() =>
            {
                DateTime time = getDateTime(current.time);
                Line line = new Line() { X1 = 0, X2 = 1000, Y1 = 0, Y2 = 0, StrokeThickness = 2, Stroke = new SolidColorBrush(Colors.Gray) };
                TextBlock text = new TextBlock() { TextWrapping = TextWrapping.Wrap, Visibility = System.Windows.Visibility.Visible, Foreground = new SolidColorBrush(Colors.Black), VerticalAlignment = System.Windows.VerticalAlignment.Center, FontSize = 24, Margin = new System.Windows.Thickness(5, 15, 5, 15), Text = "route name: " + current.routeShortName + "\ndate, time: " + time + "\ndelay: " + current.delay };
                busStopTimeTable.Children.Add(text);
                busStopTimeTable.Children.Add(line);
            });
        }

        private DateTime getDateTime(string stringTime)
        {
            long longTime = Convert.ToInt64(stringTime);
            longTime = longTime / 1000;
            DateTime epoch = new DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Utc);
            return epoch.AddSeconds(longTime);
        }

        private void Steps_Tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            NavigationService.Navigate(new Uri("/MainMenu/BushaltesInfo.xaml", UriKind.Relative));
        }

        protected override void OnBackKeyPress(System.ComponentModel.CancelEventArgs e)
        {
            e.Cancel = true;
            NavigationService.Navigate(new Uri("/MainMenu/BushaltesInfo.xaml", UriKind.Relative));
        }

    }

    [DataContract]
    public class Info
    {
        [DataMember]
        public string routeId { get; set; }
        [DataMember]
        public string routeShortName { get; set; }
        [DataMember]
        public string tripHeadsign { get; set; }
        [DataMember]
        public string time { get; set; }
        [DataMember]
        public string delay { get; set; }
        [DataMember]
        public string agencyName { get; set; }
        [DataMember]
        public int routeType { get; set; }
    }

    [DataContract]
    public class DataList
    {
        [DataMember(Name = "departures")]
        public Info[] departures { get; set; }   
        [DataMember(Name = "arrivals")]
        public Info[] arrivals { get; set; }   
    }

    [DataContract]
    public class TimeTable
    {
        [DataMember]
        public string graphId { get; set; }
        [DataMember (Name = "data")]
        public DataList data { get; set; }
    }


}