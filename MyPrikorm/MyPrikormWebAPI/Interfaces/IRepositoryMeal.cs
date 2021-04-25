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
        Task<Meal> Create(Meal meal);
        Task<Meal> Update(Meal meal);
        Task<Meal> Delete(int id);
    }
}
