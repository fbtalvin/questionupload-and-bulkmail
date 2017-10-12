/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
   $('.breadcrumb li:first a').tooltip(
   {        
      title:'home',
      placement:'top'
   }); 
   $('.breadcrumb li:first a').click(function(){
      window.location.href="../Module/jobrole-landing.html";
   });
});
