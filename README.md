# channelprocessor

/**
 *  <p>Usecase:
 *  <p>
 *  A web service to get the list of channels available on a set-top box. The implementation of this web service is as follows,
 *      <li> make a remote call to get the list of ordered channels available on a set-top box in JSON format
 *      <li> insert an attribute "channel_number" to each element in the collection and set with the value of "channel" attribute
 *      <li> return the channels list by preserving the order
 *
 * <p> Approach:
 *  <li> Using Jackson for JSON parsing. If Jackson is not required this could be replaced by 
 *      hand-written parser using StringTokenizer and other String utilities.
 *  <li> Using Jackson treaming API as Pull parser to effectively nullify memory foot print.
 *      Pull parsers iterate over input source with memory foot print of current curser.
 *  <li> Writing output as json to channels_output.json 
 *  <p>
 *  Alternate approach for parsing:
 *  1. Current parsing adopted is bit of hard-coded json structure. As JSON given is not exactly in object - array format i used this.
 * <p>
 * Compiled on : JDK 1.8
 * @author Dileep Kalidindi.
 *
 */
