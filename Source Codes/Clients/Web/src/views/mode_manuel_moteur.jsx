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
import React, { Component } from "react";//
import { Table, Grid, Row, Col } from "react-bootstrap";/*des Composants JSX pour un certain nombre de styles et de composants fournis par Bootstrap, et même mise en page CSS encapsulée en tant que composants JSX comme: <Grid>, <Row> et <Col>*/

import axios from 'axios'//bibliotheque pour chart js va utisliser dans fetch() fichier Json et recupérer les donnes en format Json
import Card from "components/Card/Card"; // core components Dathbroad react node JS  

import Button from "components/CustomButton/CustomButton";

class Icons extends Component {// deja commenter dans temperature.jsx
  constructor(props)
  {
      super(props)

      this.state = {
             // value : 0,
              op :"close",
              cl:"open",
              bool: false

      }// deja commenter 
  }
  submitHandler  = (e) => {// submit Handler c pour button 
    e.preventDefault()
 console.log(this.state)
 //192.168....
 /*axios.post('http://192.168.43.31:8080/actuator')
  .then (response => 
                {
      console.log(response)
                })
    .catch(error => {  console.log(error)})*/
    axios.get("http://192.168.43.195:5000/actuator")
    .then( response => {   console.log(response)
                            this.setState({bool : response.data
                                                                        })// va recuperer les donnes Json et les metre dans axios 
                if(this.bool===true) {this.setState({op:"open",cl:"close"});}// Close ou bien Open 
   
   } )
    .catch( error => {  console.log(error) })// catch  Exception 
    }
  render() {
    return (// render le code HTML (bootstap) suivant :
      <div className="content">
        <Grid fluid>
          <Row>
            <Col md={8} mdOffset={2}>
              <Card
                hCenter
                title="Chose your "
                category="mode"
                ctTableResponsive
                ctTableFullWidth
                ctTableUpgrade
                content={
                  <Table>
                    <thead>
                      <tr>
                        <th />
                        <th className="text-center">automatique</th>
                        <th className="text-center">Manual</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td>timing</td>
                        <td>every  3 min</td>
                        <td> none </td>
                      </tr>
                     
                      
                      <tr>
                        <td>Stable</td>
                        <td>
                          <i className="fa fa-check text-success" />
                        </td>
                        <td>
                          <i className="fa fa-times text-danger" />
                        </td>
                        
                      </tr>
                     
                      
                      <tr>
                        <td />
                        <td>
                        
                        <Button
                            
                            href=""
                            round
                            fill
                            disabled
                            
                          >
                            Garde state 
                          </Button>
                        </td>
                       <td>
                       <form onSubmit={this.submitHandler}>// listner sur submit 
                        <Button  onClick={this.changeHandler1}
                           
                            round
                            fill
                            target="_blank"
                            bsStyle="default"
                          >
                            Start
                        </Button>
                        </form>  
                        </td>
                      </tr>
                    
                    <tr>//les etat de moteur  cl et op des var Close ou bien Open 
                        <td>state:</td>
                        <td>{this.state.cl }</td>
                        <td> {this.state.op}</td>
                      </tr>
                      </tbody>
                  </Table>
                }
              />
            </Col>
          </Row>
        </Grid>
      </div>
    );
  }
}

export default Icons;
