/*!

=========================================================
* Light Bootstrap Dashboard React - v1.3.0
=========================================================

* Product Page: https://www.creative-tim.com/product/light-bootstrap-dashboard-react
* Copyright 2019 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/light-bootstrap-dashboard-react/blob/master/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
import axios from 'axios';//bibliotheque pour chart js va utisliser dans fetch() fichier Json et recupérer les donnes en format Json  
import React, { Component } from "react";
import ChartistGraph from "react-chartist";//package pour graphe chartJs 
import { Grid, Row, Col } from "react-bootstrap";/*des Composants JSX pour un certain nombre de styles et de composants fournis par Bootstrap, et même mise en page CSS encapsulée en tant que composants JSX comme: <Grid>, <Row> et <Col>*/
import temperature from "assets/img/temperature.jpg";//importation d icon temperature
import { Card } from "components/Card/Card.jsx";// core components Dathbroad react node JS  
import { StatsCard } from "components/StatsCard/StatsCard.jsx";//start components card Dathbroad react node JS 
import {  optionsSales, responsiveSales
} from "variables/Variables.jsx";

class Dashboard extends Component {
  constructor(props)
  {
      super(props)//props :des éléments React décrivant ce qui doit apparaître à l’écran. statique(inchangeable) /props(s) vs stat(d)/
      

      this.state = {// state attribut de component :stat de l'ecran (view) ,c est dynamique 
                  
                  temperature : [],//tableau temperature qui va recoi les donnes  Http
                  tmp : []
      }
      
  }
componentDidMount(){
//La méthode componentDidMount() est l'endroit parfait pour appeler la méthode setState() afin de changer l'état de l'application tandis que render() se charge des données JSX mise à jour   
    
    
    

    //https://jsonplaceholder.typicode.com/posts"http://192.168.43.85:5000/sensors/1
 //   http://localhost/json.json
axios.get("https://jsonplaceholder.typicode.com/posts")
.then( response => {   console.log(response)//consol.log equivalent document.write dans Js // afficher 
                         this.setState({temperature : response.data.valuesOfDay// VAL DE chaque 8 fois au jour chaque 3h 
                                                                              })
                                                                              
//axios va recuperer les donnes temperatures en formats Json ,chaque 3h va update une nouvel valeur et comme ca on va construire graphe
} )
 .catch( error => {  console.log(error) })// si ila ya une Exception afficher error 

              
     }
  

  
  render() {// redner c comme return 
    var dataSales = {   labels: [// axe de temps  
      "0:00PM",
      "3:00PM",
      "6:00PM",
      "9:00PM",
      "12:00PM",
      "15:00PM",
      "18:00PM",
      "21:00PM",
    ],
    series: //[ this.state.tmp  ]};//  val chaque 3h pour axe Y
    [18.4, 19, 19.43, 19.87, 20.1 , 23 , 30 , 27.4],
  };
    var time = new Date().getTime();
    var date = new Date(time);
       
    return (// code HTML qui on a creer avec bootstrap4  et Css3     
      <div className="content">
        <Grid fluid>
          <Row>
            <Col lg={8} sm={5}>
              <StatsCard
              // bigIcon={<i className="pe-7s-server text-warning" />} 
              bigIcon={<img alt="tmp" src={temperature}  width="100px" height="80px " ></img>}
                statsText="Now"
                statsValue={<h3> 23.16 °C</h3>}
               statsIcon={<a href="/admin/dashboard" className="fa fa-refresh" ></a>}
                statsIconText="Updated now"
              />
            </Col>
            
          </Row>
          <Row>
            <Col md={20}>
              <Card
                statsIcon="fa fa-history"
                id="chartHours"
                title="today"
                category="24 Hours values"
                stats= { date.toString()} 
             content={
                  <div className="ct-chart "  >
                    <ChartistGraph
                      data={dataSales}
                      type="Line"
                      options={optionsSales}
                      responsiveOptions={responsiveSales}
                    />
                  </div>
                }
                
              />
            </Col>
           

            
          </Row>
        </Grid>
      </div>
    );
  }
}

export default Dashboard;
