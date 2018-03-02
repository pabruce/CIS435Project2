/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis435project2phase1;

//import java.time.Instant;
import java.util.Random;
import java.math.BigInteger;
/**
 *
 * @author Forrest
 */
public class KeyGen {
    public int generateRandomBigPrime()
    {
        long seed;
        int output;
        int[] primes = new int[]{130681,130687,130693,130699,130729,130769,130783,130787,130807,130811,130817,130829,130841,130843,130859,130873,130927,130957,130969,130973,130981,130987,131009,131011,131023,131041,131059,131063,131071,131101,131111,131113,131129,131143,131149,131171,131203,131213,131221,131231,131249,131251,131267,131293,131297,131303,131311,131317,131321,131357,131363,131371,131381,131413,131431,131437,131441,131447,131449,131477,131479,131489,131497,131501,131507,131519,131543,131561,131581,131591,131611,131617,131627,131639,131641,131671,131687,131701,131707,131711,131713,131731,131743,131749,131759,131771,131777,131779,131783,131797,131837,131839,131849,131861,131891,131893,131899,131909,131927,131933,131939,131941,131947,131959,131969,132001,132019,132047,132049,132059,132071,132103,132109,132113,132137,132151,132157,132169,132173,132199,132229,132233,132241,132247,132257,132263,132283,132287,132299,132313,132329,132331,132347,132361,132367,132371,132383,132403,132409,132421,132437,132439,132469,132491,132499,132511,132523,132527,132529,132533,132541,132547,132589,132607,132611,132619,132623,132631,132637,132647,132661,132667,132679,132689,132697,132701,132707,132709,132721,132739,132749,132751,132757,132761,132763,132817,132833,132851,132857,132859,132863,132887,132893,132911,132929,132947,132949,132953,132961,132967,132971,132989,133013,133033,133039,133051,133069,133073,133087,133097,133103,133109,133117,133121,133153,133157,133169,133183,133187,133201,133213,133241,133253,133261,133271,133277,133279,133283,133303,133319,133321,133327,133337,133349,133351,133379,133387,133391,133403,133417,133439,133447,133451,133481,133493,133499,133519,133541,133543,133559,133571,133583,133597,133631,133633,133649,133657,133669,133673,133691,133697,133709,133711,133717,133723,133733,133769,133781,133801,133811,133813,133831,133843,133853,133873,133877,133919,133949,133963,133967,133979,133981,133993,133999,134033,134039,134047,134053,134059,134077,134081,134087,134089,134093,134129,134153,134161,134171,134177,134191,134207,134213,134219,134227,134243,134257,134263,134269,134287,134291,134293,134327,134333,134339,134341,134353,134359,134363,134369,134371,134399,134401,134417,134437,134443,134471,134489,134503,134507,134513,134581,134587,134591,134593,134597,134609,134639,134669,134677,134681,134683,134699,134707,134731,134741,134753,134777,134789,134807,134837,134839,134851,134857,134867,134873,134887,134909,134917,134921,134923,134947,134951,134989,134999,135007,135017,135019,135029,135043,135049,135059,135077,135089,135101,135119,135131,135151,135173,135181,135193,135197,135209,135211,135221,135241,135257,135271,135277,135281,135283,135301,135319,135329,135347,135349,135353,135367,135389,135391,135403,135409,135427,135431,135433,135449,135461,135463,135467,135469,135479,135497,135511,135533,135559,135571,135581,135589,135593,135599,135601,135607,135613,135617,135623,135637,135647,135649,135661,135671,135697,135701,135719,135721,135727,135731,135743,135757,135781,135787,135799,135829,135841,135851,135859,135887,135893,135899,135911,135913,135929,135937,135977,135979,136013,136027,136033,136043,136057,136067,136069,136093,136099,136111,136133,136139,136163,136177,136189,136193,136207,136217,136223,136237,136247,136261,136273,136277,136303,136309,136319,136327,136333,136337,136343,136351,136361,136373,136379,136393,136397,136399,136403,136417,136421,136429,136447,136453,136463,136471,136481,136483,136501,136511,136519,136523,136531,136537,136541,136547,136559,136573,136601,136603,136607,136621,136649,136651,136657,136691,136693,136709,136711,136727,136733,136739,136751,136753,136769,136777,136811,136813,136841,136849,136859,136861,136879,136883,136889,136897,136943,136949,136951,136963,136973,136979,136987,136991,136993,136999,137029,137077,137087,137089,137117,137119,137131,137143,137147,137153,137177,137183,137191,137197,137201,137209,137219,137239,137251,137273,137279,137303,137321,137339,137341,137353,137359,137363,137369,137383,137387,137393,137399,137413,137437,137443,137447,137453,137477,137483,137491,137507,137519,137537,137567,137573,137587,137593,137597,137623,137633,137639,137653,137659,137699,137707,137713,137723,137737,137743,137771,137777,137791,137803,137827,137831,137849,137867,137869,137873,137909,137911,137927,137933,137941,137947,137957,137983,137993,137999,138007,138041,138053,138059,138071,138077,138079,138101,138107,138113,138139,138143,138157,138163,138179,138181,138191,138197,138209,138239,138241,138247,138251,138283,138289,138311,138319,138323,138337,138349,138371,138373,138389,138401,138403,138407,138427,138433,138449,138451,138461,138469,138493,138497,138511,138517,138547,138559,138563,138569,138571,138577,138581,138587,138599,138617,138629,138637,138641,138647,138661,138679,138683,138727,138731,138739,138763,138793,138797,138799,138821,138829,138841,138863,138869,138883,138889,138893,138899,138917,138923,138937,138959,138967,138977,139021,139033,139067,139079,139091,139109,139121,139123,139133,139169,139177,139187,139199,139201,139241,139267,139273,139291,139297,139301,139303,139309,139313,139333,139339,139343,139361,139367,139369,139387,139393,139397,139409,139423,139429,139439,139457,139459,139483,139487,139493,139501,139511,139537,139547,139571,139589,139591,139597,139609,139619,139627,139661,139663,139681,139697,139703,139709,139721,139729,139739,139747,139753,139759,139787,139801,139813,139831,139837,139861,139871,139883,139891,139901,139907,139921,139939,139943,139967,139969,139981,139987,139991,139999,140009,140053,140057,140069,140071,140111,140123,140143,140159,140167,140171,140177,140191,140197,140207,140221,140227,140237,140249,140263,140269,140281,140297,140317,140321,140333,140339,140351,140363,140381,140401,140407,140411,140417,140419,140423,140443,140449,140453,140473,140477,140521,140527,140533,140549,140551,140557,140587,140593,140603,140611,140617,140627,140629,140639,140659,140663,140677,140681,140683,140689,140717,140729,140731,140741,140759,140761,140773,140779,140797,140813,140827,140831,140837,140839,140863,140867,140869,140891,140893,140897,140909,140929,140939,140977,140983,140989,141023,141041,141061,141067,141073,141079,141101,141107,141121,141131,141157,141161,141179,141181,141199,141209,141221,141223,141233,141241,141257,141263,141269,141277,141283,141301,141307,141311,141319,141353,141359,141371,141397,141403,141413,141439,141443,141461,141481,141497,141499,141509,141511,141529,141539,141551,141587,141601,141613,141619,141623,141629,141637,141649,141653,141667,141671,141677,141679,141689,141697,141707,141709,141719,141731,141761,141767,141769,141773,141793,141803,141811,141829,141833,141851};
        //seed = Instant.now().getEpochSecond();
        Random rand = new Random();
        seed = rand.nextInt(999999);
        output = Math.toIntExact(seed);
        output = primes[output % primes.length];
        return output;
    }
    
    public final class GenerateKeyPair() 
    {
        int bitlength = 1024;
         
        BigInteger e,d,n;
        BigInteger p,q;
        
        Random rand = new Random();
        
       p = BigInteger.probablePrime(bitlength,rand);
       q = BigInteger.probablePrime(bitlength,rand);
       n = p.multiply(q);
       
       pqSub = p.subtract(BigInteger.ONE)
        
    }
}
