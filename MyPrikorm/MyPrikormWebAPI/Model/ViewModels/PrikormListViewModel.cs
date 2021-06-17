using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace MyPrikormWebAPI.Model.ViewModels
{
    public class PrikormListViewModel
    {
        public int Id { get; set; }
        public int IdUser { get; set; }
        public string DateMeal { get; set; }
        public string Meal { get; set; }
        public string Product { get; set; }
        public int Weight { get; set; }
        public string Reaction { get; set; }
    }
}
