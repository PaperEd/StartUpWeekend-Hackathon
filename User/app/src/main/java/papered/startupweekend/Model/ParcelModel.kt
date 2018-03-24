package papered.startupweekend.Model

/**
 * Created by PaperEd on 2018-03-25.
 */
data class ParcelModel(var approval : String,
                       var arrivalDetailAddress: String,
                       var arrivalPoint : String,
                       var itemStatus : Boolean,
                       var itemWeight:String,
                       var largeItemCount : String,
                       var middleItemCount : String,
                       var smallItemCount : String,
                       var startingPoint : String) {
}