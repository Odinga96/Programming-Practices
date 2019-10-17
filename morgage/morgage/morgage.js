function computeRepaymentAmount() {
   //number of year for repayment
  var number_Of_Years=document.getElementById('years').value;

  //ammount user wants to apply for
  var P=document.getElementById('ammount').value;

  //frequency of payment
  var period_option=document.getElementById('option').value;


  // message area
  var message_Area=document.getElementById('message');


  //get the  tatal perion if payment N
  var N=getN(number_Of_Years, period_option);

  var r=(6.5/Number(period_option))/100;

  var discount=getDiscount(P);
     P=((100-discount)*P)/100;

   var numerator=r*P*(Math.pow((1+r),N));
   var denomenator=(Math.pow((1+r),N)) -1;

  var amount=(numerator/denomenator);

   var period_name=(period_option == 1 )?"year":
              (period_option == 4 )?"quarter":
              (period_option == 12 )?"month":
              (period_option == 26 )?"fortnight":
              "week";

          


  message_Area.innerText="The repayment amount is "+amount.toFixed(2)+" each "+period_name;
}

   // Discount
// 5.4% on money borrowed up to $200 000
// o 5.09% on money borrowed from $200 000 to $250 000
// o 4.84% on money borrowed from $250 000 to $500 000
// o 4.79% on money borrowed over $500 000 to $750 000
// o 4.5% on money borrowed over $750 000

function getDiscount(ammount){

        if (ammount<=200000)
            return 5.4;
       else if (ammount>200000 && ammount<=250000)
            return 5.09;
       else if (ammount>250000 && ammount<=500000)
           return 4.84;
       else if (ammount>500000 && ammount<=750000)
            return 4.79;
       else if (ammount>750000)
            return 4.5;
}



//gets and returns the total installments
function getN(years, period_frequency) {

  var N=0;

  // check if user wants to subscribe to yearly payment
  if (period_frequency == '1')
      N=years;

// for quarterly payment we multiply by four
  else if (period_frequency == '4')
      N=years*4;

// for monthly payment multiply by 12
  else if (period_frequency == '12')
      N=years*12;

// a fortnightly is every 2 weeks
 else if (period_frequency == '26')
     N=years*26;

//weekly payment
  else if (period_frequency == '52')
      N=years*52;

return N;
}
