using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MyPrikormWebAPI.Model.DB.Entities;


namespace MyPrikormWebAPI.Interfaces
{
    interface IRepositoryMeal
    {
        Task<List<Meal>> GetAll();
        Task<Meal> Get(int id);
        Meal Create(Meal meal);
        Meal Update(Meal meal);
        Meal Delete(int id);
    }
}
